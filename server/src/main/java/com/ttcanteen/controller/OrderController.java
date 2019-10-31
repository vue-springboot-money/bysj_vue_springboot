package com.ttcanteen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttcanteen.dto.OrderDto;
import com.ttcanteen.pojo.Common;
import com.ttcanteen.pojo.ResultPojo;
import com.ttcanteen.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController()
@Api(description = "订单相关接口")
@Transactional
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 创建订单
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("order")
	@ApiOperation("创建订单")
	public ResultPojo createUser(@ModelAttribute OrderDto orderDto) {

		int result = orderService.createOrder(orderDto);

		// 创建成功
		if (result == 1) {
			return new ResultPojo(Common.OK, orderDto);
		} else {
			// 创建失败
			return new ResultPojo(Common.ERR, orderDto);
		}
	}
}
