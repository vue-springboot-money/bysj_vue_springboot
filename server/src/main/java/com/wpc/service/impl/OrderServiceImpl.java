package com.wpc.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wpc.dto.OrderDto;
import com.wpc.dto.OrderItemDto;
import com.wpc.entity.TbOrderEntity;
import com.wpc.entity.TbOrderItemEntity;
import com.wpc.mapper.TbGoodMapper;
import com.wpc.mapper.TbOrderItemMapper;
import com.wpc.mapper.TbOrderMapper;
import com.wpc.service.OrderService;
import com.wpc.utils.CodeGeneratorUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private TbGoodMapper tbMenuMapper;

	@Autowired
	private TbOrderMapper tbOrderMapper;

	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;

	@Override
	@Transactional
	public int createOrder(OrderDto orderDto) {
		// 用户id
		Long uid = orderDto.getUid();
		// 菜单集合
		List<OrderItemDto> itemList = orderDto.getItemList();

		// 生成订单编号
		String no = UUID.randomUUID().toString().replace("-", "");

		float priceSum = 0;

		// 遍历所有菜单
		for (OrderItemDto item : itemList) {
			// 转换菜单id
			Long mid = item.getId();

			// 获取菜品单价
			float price = tbMenuMapper.selectByPrimaryKey(mid).getPrice();

			// 获取购买数量
			int amount = item.getCount();

			// 累加价格
			priceSum += price + amount;

			// 创建订单详情对象
			TbOrderItemEntity orderItemEntity = new TbOrderItemEntity();
			orderItemEntity.setNo(no);
			orderItemEntity.setMid(mid);
			orderItemEntity.setAmount(amount);
			orderItemEntity.setPrice(price * amount);

			// 插入数据库
			tbOrderItemMapper.insert(orderItemEntity);
		}

		// 生成取餐码
		String code = "";
		while (true) {
			code = CodeGeneratorUtil.generator();
			if (tbOrderMapper.selectByCode(code) == 0) {
				break;
			}
		}

		// 创建订单对象
		TbOrderEntity orderEntity = new TbOrderEntity();
		orderEntity.setComment("");
		orderEntity.setUid(uid);
		orderEntity.setNo(no);
		orderEntity.setPrice(priceSum);
		orderEntity.setState((byte) 0);
		orderEntity.setCode(code);

		// 插入结果
		int insertResult = tbOrderMapper.insert(orderEntity);

		return insertResult;
	}

	@Override
	public List<TbOrderEntity> getOrderListByUid(Long uid) {
		List<TbOrderEntity> selectResult = tbOrderMapper.selectByUid(uid);
		return selectResult;
	}

	@Override
	public List<TbOrderItemEntity> getOrderItemByNo(String no) {
		List<TbOrderItemEntity> selectResult = tbOrderItemMapper.selectByNo(no);
		return selectResult;
	}

	@Override
	public List<TbOrderEntity> selectOrderListByPage(int pageNum) {
		int count = 10;
		return tbOrderMapper.selectAll((pageNum - 1) * count, count);
	}

	@Override
	public int selectOrderTotal() {
		return tbOrderMapper.selectCount();
	}

	@Override
	public List<TbOrderItemEntity> takeMeal(String code) {
		int updateResult = tbOrderMapper.updateOrderByCode(code);
		
		if (updateResult == 1) {
			return tbOrderItemMapper.selectByNo(tbOrderMapper.selectOrderByCode(code).getNo());
		}
		
		return null;
	}

}
