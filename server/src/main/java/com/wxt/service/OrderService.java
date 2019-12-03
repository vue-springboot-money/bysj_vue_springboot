package com.wxt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wxt.entity.TbOrderEntity;
import com.wxt.mapper.TbOrderMapper;

@Service
public class OrderService {

	@Autowired
	private TbOrderMapper tbOrderMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建订单
	 * 
	 * @param entity
	 * @return
	 */
	public int createOrder(TbOrderEntity entity) {
		return tbOrderMapper.insert(entity);
	}

	/**
	 * 更新订单信息
	 * 
	 * @param entity
	 * @return
	 */
	public int updateOrder(TbOrderEntity entity) {
		return tbOrderMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的订单
	 * 
	 * @param id
	 * @return
	 */
	public TbOrderEntity getOrderById(Long id) {
		return tbOrderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的订单
	 * 
	 * @param id
	 * @return
	 */
	public int deleteOrderById(Long id) {
		return tbOrderMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<TbOrderEntity> getOrderListByPage(int page) {
		return tbOrderMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 查询订单总数
	 * 
	 * @return
	 */
	public int getOrderCount() {
		return tbOrderMapper.selectCount();
	}
}
