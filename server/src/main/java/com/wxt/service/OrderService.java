package com.wxt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wxt.entity.TbOrderEntity;
import com.wxt.entity.TbOrderItemEntity;
import com.wxt.entity.TbOrderTempEntity;
import com.wxt.mapper.TbMenuMapper;
import com.wxt.mapper.TbOrderItemMapper;
import com.wxt.mapper.TbOrderMapper;
import com.wxt.mapper.TbOrderTempMapper;

@Service
public class OrderService {

	@Autowired
	private TbOrderMapper tbOrderMapper;
	
	@Autowired
	private TbMenuMapper tbMenuMapper;

	@Autowired
	private TbOrderTempMapper tbOrderTempMapper;

	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建
	 * 
	 * @param entity
	 * @return
	 */
	public int createOrder(TbOrderEntity entity) {
		// 获取台号id
		Long did = entity.getDid();
		
		float price = 0;
		
		// check是否已全部签单
		List<TbOrderTempEntity> tempList = tbOrderTempMapper.selectByDid(did);
		for (TbOrderTempEntity temp : tempList) {
			if (temp.getState() != 1) {
				// 存在未签单菜品
				return -1;
			}
			
			price += tbMenuMapper.selectByPrimaryKey(temp.getMid()).getPrice() * temp.getAmount();
		}
		
		int insertResult = 0;
		
		// 生成订单编号
		String no = UUID.randomUUID().toString().replace("-", "");
		entity.setNo(no);
		entity.setPrice(price);
		entity.setState((byte) 0);
		// 插入订单
		insertResult = tbOrderMapper.insert(entity);
		
		// 插入订单详情
		List<TbOrderItemEntity> orderItemList = new ArrayList<>();
		
		for (TbOrderTempEntity temp : tempList) {
			TbOrderItemEntity orderItemEntity = new TbOrderItemEntity();
			orderItemEntity.setMid(temp.getMid());
			orderItemEntity.setNo(no);
			orderItemEntity.setPrice(tbMenuMapper.selectByPrimaryKey(temp.getMid()).getPrice());
			orderItemEntity.setAmount(temp.getAmount());
			orderItemList.add(orderItemEntity);
		}
		
		for (TbOrderItemEntity item : orderItemList) {
			insertResult = tbOrderItemMapper.insert(item);
		}
		
		// 删除点菜单
		for (TbOrderTempEntity temp : tempList) {
			tbOrderTempMapper.deleteByPrimaryKey(temp.getId());
		}
		
		return insertResult;
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 */
	public int updateOrder(TbOrderEntity entity) {
		return tbOrderMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	public TbOrderEntity getOrderById(Long id) {
		return tbOrderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	public int deleteOrderById(Long id) {
		return tbOrderMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @return
	 */
	public List<TbOrderEntity> getOrderListByPage(int page) {
		return tbOrderMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 查询数据总数
	 * 
	 * @return
	 */
	public int getOrderCount() {
		return tbOrderMapper.selectCount();
	}

}
