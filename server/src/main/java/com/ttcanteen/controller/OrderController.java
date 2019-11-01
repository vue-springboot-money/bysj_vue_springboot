package com.ttcanteen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ttcanteen.dto.OrderDto;
import com.ttcanteen.entity.TbOrderEntity;
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
	public ResultPojo createOrder(@RequestBody OrderDto orderDto) {

		int result = orderService.createOrder(orderDto);

		// 创建成功
		if (result == 1) {
			return new ResultPojo(Common.OK, orderDto);
		} else {
			// 创建失败
			return new ResultPojo(Common.ERR, orderDto);
		}
	}

	@GetMapping("orders/uid/{uid}")
	@ApiOperation("根据用户id查询订单列表")
	public ResultPojo getOrderListByUid(@PathVariable Long uid) {
		List<TbOrderEntity> orderList = orderService.getOrderListByUid(uid);

		// 查询失败
		if (orderList == null) {
			return new ResultPojo(Common.ERR, uid);
		} else {
			// 查询成功
			return new ResultPojo(Common.OK, orderList);
		}
	}
	
	@GetMapping("order/{no}")
	@ApiOperation("根据订单编号查询订单列表")
	public ResultPojo getOrderListByNo(@PathVariable String no) {
		List<TbOrderEntity> orderList = orderService.getOrderListByNo(no);

		// 查询失败
		if (orderList == null) {
			return new ResultPojo(Common.ERR, no);
		} else {
			// 查询成功
			return new ResultPojo(Common.OK, orderList);
		}
	}
}
