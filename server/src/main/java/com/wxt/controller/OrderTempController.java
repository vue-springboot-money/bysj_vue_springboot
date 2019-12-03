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

import com.wxt.entity.TbOrderTempEntity;
import com.wxt.pojo.Common;
import com.wxt.pojo.ResultPojo;
import com.wxt.service.OrderTempService;

@RestController()
@Transactional
public class OrderTempController {

	@Autowired
	private OrderTempService orderTempService;

	/**
	 * 创建
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("orderTemp")
	public ResultPojo createOrderTemp(@RequestBody TbOrderTempEntity entity) {

		int result = orderTempService.createOrderTemp(entity);

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
	@PatchMapping("orderTemp")
	public ResultPojo modifyOrderTemp(@RequestBody TbOrderTempEntity entity) {
		int updateResult = orderTempService.updateOrderTemp(entity);

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
	@GetMapping("orderTemp/id/{id}")
	public ResultPojo getOrderTempById(@PathVariable Long id) {
		TbOrderTempEntity result = orderTempService.getOrderTempById(id);

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
	@DeleteMapping("orderTemp/id/{id}")
	public ResultPojo deleteOrderTemp(@PathVariable Long id) {
		int deleteResult = orderTempService.deleteOrderTempById(id);

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
	@GetMapping("orderTemp/page/{page}")
	public ResultPojo getOrderTempListByPage(@PathVariable int page) {
		List<TbOrderTempEntity> orderTempList = orderTempService.getOrderTempListByPage(page);

		// 查询成功
		if (orderTempList == null || orderTempList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, orderTempList);
		}
	}

	/**
	 * 获取所有数据
	 * @return
	 */
	@GetMapping("orderTemp/count")
	public ResultPojo getOrderTempCount() {
		return new ResultPojo(Common.OK, orderTempService.getOrderTempCount());
	}
}
