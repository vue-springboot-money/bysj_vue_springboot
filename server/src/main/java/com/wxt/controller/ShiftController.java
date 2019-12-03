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

import com.wxt.entity.TbShiftEntity;
import com.wxt.pojo.Common;
import com.wxt.pojo.ResultPojo;
import com.wxt.service.ShiftService;

@RestController()
@Transactional
public class ShiftController {

	@Autowired
	private ShiftService shiftService;

	/**
	 * 创建
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("shift")
	public ResultPojo createShift(@RequestBody TbShiftEntity entity) {

		int result = shiftService.createShift(entity);

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
	@PatchMapping("shift")
	public ResultPojo modifyShift(@RequestBody TbShiftEntity entity) {
		int updateResult = shiftService.updateShift(entity);

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
	@GetMapping("shift/id/{id}")
	public ResultPojo getShiftById(@PathVariable Long id) {
		TbShiftEntity result = shiftService.getShiftById(id);

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
	@DeleteMapping("shift/id/{id}")
	public ResultPojo deleteShift(@PathVariable Long id) {
		int deleteResult = shiftService.deleteShiftById(id);

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
	@GetMapping("shift/page/{page}")
	public ResultPojo getShiftListByPage(@PathVariable int page) {
		List<TbShiftEntity> shiftList = shiftService.getShiftListByPage(page);

		// 查询成功
		if (shiftList == null || shiftList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, shiftList);
		}
	}

	/**
	 * 获取所有数据
	 * @return
	 */
	@GetMapping("shift/count")
	public ResultPojo getShiftCount() {
		return new ResultPojo(Common.OK, shiftService.getShiftCount());
	}
}
