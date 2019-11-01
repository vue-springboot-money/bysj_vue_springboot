package com.ttcanteen.service;

import java.util.List;

import com.ttcanteen.dto.OrderDto;
import com.ttcanteen.entity.TbOrderEntity;

public interface OrderService {

	/**
	 * 创建订单
	 * @return
	 */
	public int createOrder(OrderDto orderDto);

	/**
	 * 获取指定用户id的所有订单
	 * @param uid
	 * @return
	 */
	public List<TbOrderEntity> getOrderListByUid(Long uid);

	/**
	 * 根据订单编号查询订单
	 * @param no
	 * @return
	 */
	public List<TbOrderEntity> getOrderListByNo(String no);
}
