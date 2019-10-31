package com.ttcanteen.service;

import com.ttcanteen.dto.OrderDto;

public interface OrderService {

	/**
	 * 创建订单
	 * @return
	 */
	public int createOrder(OrderDto orderDto);
}
