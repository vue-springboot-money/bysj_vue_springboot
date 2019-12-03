package com.wxt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wxt.entity.TbOrderItemEntity;
import com.wxt.pojo.Common;
import com.wxt.pojo.ResultPojo;
import com.wxt.service.OrderItemService;

@RestController()
@Transactional
public class OrderItemController {

	@Autowired
	private OrderItemService orderItemService;

	/**
	 * 创建
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("orderItem")
	public ResultPojo createOrderItem(@RequestBody TbOrderItemEntity entity) {

		int result = orderItemService.createOrderItem(entity);

		// 注册失败
		if (result != 1) {
			return new ResultPojo(Common.ERR, entity);
		} else {
			// 注册成功
			return new ResultPojo(Common.OK, entity);
		}
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("orderItem")
	public ResultPojo modifyOrderItem(@RequestBody TbOrderItemEntity entity) {
		int updateResult = orderItemService.updateOrderItem(entity);

		// 更新成功
		if (updateResult != 0) {
			return new ResultPojo(Common.OK, entity);
		} else {
			// 更新失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 查询指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("orderItem/id/{id}")
	public ResultPojo getOrderItemById(@PathVariable Long id) {
		TbOrderItemEntity result = orderItemService.getOrderItemById(id);

		// 查询成功
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			// 查询失败
			return new ResultPojo(Common.ERR, null);
		}
	}
	
	/**
	 * 删除指定id的数据
	 * @param id
	 * @return
	 */
	@DeleteMapping("orderItem/id/{id}")
	public ResultPojo deleteOrderItem(@PathVariable Long id) {
		int deleteResult = orderItemService.deleteOrderItemById(id);

		// 删除成功
		if (deleteResult == 1) {
			return new ResultPojo(Common.OK, deleteResult);
		} else {
			// 删除失败
			return new ResultPojo(Common.ERR, null);
		}
	}
	
	/**
	 * 分页查询
	 * @param pageNum
	 * @return
	 */
	@GetMapping("orderItem/page/{page}")
	public ResultPojo getOrderItemListByPage(@PathVariable int page) {
		List<TbOrderItemEntity> orderItemList = orderItemService.getOrderItemListByPage(page);

		// 查询成功
		if (orderItemList == null || orderItemList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, orderItemList);
		}
	}

	/**
	 * 获取所有数据
	 * @return
	 */
	@GetMapping("orderItem/count")
	public ResultPojo getOrderItemCount() {
		return new ResultPojo(Common.OK, orderItemService.getOrderItemCount());
	}
}
