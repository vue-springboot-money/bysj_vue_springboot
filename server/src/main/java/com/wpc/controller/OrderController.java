package com.wpc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wpc.dto.OrderDto;
import com.wpc.entity.TbOrderEntity;
import com.wpc.pojo.Common;
import com.wpc.pojo.ResultPojo;
import com.wpc.service.OrderService;

@RestController()
@Transactional
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 创建订单
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("order")
	public ResultPojo createOrder(@RequestBody OrderDto dto) {

		int result = orderService.createOrder(dto);

		// 注册失败
		if (result != 1) {
			return new ResultPojo(Common.ERR, dto);
		} else {
			// 注册成功
			return new ResultPojo(Common.OK, dto);
		}
	}

	/**
	 * 更新订单
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("order")
	public ResultPojo modifyOrder(@RequestBody TbOrderEntity entity) {
		int updateResult = orderService.updateOrder(entity);

		// 更新成功
		if (updateResult != 0) {
			return new ResultPojo(Common.OK, entity);
		} else {
			// 更新失败
			return new ResultPojo(Common.ERR, null);
		}
	}
	
	/**
	 * 更新订单
	 * 
	 * @param entity
	 * @return
	 */
	@PutMapping("order/id/{id}/state/{state}")
	public ResultPojo updateStateById(@PathVariable Long id, @PathVariable byte state) {
		int updateResult = orderService.updateOrderState(id, state);

		// 更新成功
		if (updateResult != 0) {
			return new ResultPojo(Common.OK, updateResult);
		} else {
			// 更新失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 查询指定id的订单
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("order/{id}")
	public ResultPojo getOrderById(@PathVariable Long id) {
		TbOrderEntity result = orderService.getOrderById(id);

		// 查询成功
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			// 查询失败
			return new ResultPojo(Common.ERR, null);
		}
	}
	
	/**
	 * 删除指定id的订单
	 * @param id
	 * @return
	 */
	@DeleteMapping("order/{id}")
	public ResultPojo deleteOrder(@PathVariable Long id) {
		int deleteResult = orderService.deleteOrderById(id);

		// 删除成功
		if (deleteResult == 1) {
			return new ResultPojo(Common.OK, deleteResult);
		} else {
			// 删除失败
			return new ResultPojo(Common.ERR, null);
		}
	}
	
	/**
	 * 根据用户id查询订单
	 * @param uid
	 * @return
	 */
	@GetMapping("order/uid/{uid}")
	public ResultPojo getOrderListByUid(@PathVariable Long uid) {
		List<OrderDto> orderList = orderService.getOrderListByUid(uid);

		// 查询成功
		if (orderList == null || orderList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, orderList);
		}
	}
	
	/**
	 * 分页查询
	 * @param pageNum
	 * @return
	 */
	@GetMapping("order/all")
	public ResultPojo getOrderListAll() {
		List<OrderDto> orderList = orderService.getOrderListAll();

		// 查询成功
		if (orderList == null || orderList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, orderList);
		}
	}

	/**
	 * 获取所有订单数量
	 * @return
	 */
	@GetMapping("order/count")
	public ResultPojo getOrderCount() {
		return new ResultPojo(Common.OK, orderService.getOrderCount());
	}
}
