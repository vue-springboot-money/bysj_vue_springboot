package com.gx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gx.dto.OrderDto;
import com.gx.dto.OrderItemDto;
import com.gx.entity.TbOrderEntity;
import com.gx.entity.TbOrderItemEntity;
import com.gx.pojo.Common;
import com.gx.pojo.ResultPojo;
import com.gx.service.GoodService;
import com.gx.service.OrderItemService;
import com.gx.service.OrderService;
import com.gx.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController()
@Api(description = "订单相关接口")
@Transactional
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private UserService userService;

	@Autowired
	private GoodService goodService;

	/**
	 * 创建订单
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("order")
	@ApiOperation("创建订单")
	public ResultPojo createOrder(@RequestBody OrderDto orderDto) {

		// check余额是否够
		if (userService.findUserById(orderDto.getUid()).getBalance() < orderDto.getPrice()) {
			// 返回余额不足信息
			return new ResultPojo("账户余额不足", orderDto);
		}

		// 够，创建订单，创建订单详情，生成取餐码
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

	@GetMapping("orderList/{pageNum}")
	@ApiOperation("分页查询订单")
	public ResultPojo getUserListByPage(@PathVariable int pageNum) {
		List<TbOrderEntity> orderList = orderService.selectOrderListByPage(pageNum);

		// 查询失败（没有数据）
		if (orderList == null || orderList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询成功
			List<OrderDto> orderDtoList = new ArrayList<>();
			for (TbOrderEntity order : orderList) {
				OrderDto orderDto = new OrderDto();
				orderDto.setOrderEntity(order);
				orderDto.setUser(userService.findUserById(order.getUid()));
				String no = order.getNo();
				List<TbOrderItemEntity> orderItemList = orderItemService.selectOrderItemByNo(no);
				List<OrderItemDto> orderItemDtoList = new ArrayList<>();
				for (TbOrderItemEntity orderItem : orderItemList) {
					OrderItemDto orderItemDto = new OrderItemDto();
					orderItemDto.setId(orderItem.getId());
					orderItemDto.setName(goodService.findGoodById(orderItem.getMid()).getName());
					orderItemDto.setPrice(orderItem.getPrice());
					orderItemDto.setCount(orderItem.getAmount());
					orderItemDtoList.add(orderItemDto);
				}
				orderDto.setItemList(orderItemDtoList);
				orderDtoList.add(orderDto);
			}
			return new ResultPojo(Common.OK, orderDtoList);
		}
	}

	@GetMapping("orderTotal")
	@ApiOperation("查询订单总数")
	public ResultPojo getUserTotal() {
		return new ResultPojo(Common.OK, orderService.selectOrderTotal());
	}

	@GetMapping("order/takeMeal/{code}")
	@ApiOperation("取餐")
	public ResultPojo takeMeal(@PathVariable String code) {
		List<TbOrderItemEntity> mealList = orderService.takeMeal(code);
		return new ResultPojo(Common.OK, mealList);
	}
}
