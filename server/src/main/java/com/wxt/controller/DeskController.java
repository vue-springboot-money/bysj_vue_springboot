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

import com.wxt.entity.TbDeskEntity;
import com.wxt.pojo.Common;
import com.wxt.pojo.ResultPojo;
import com.wxt.service.DeskService;

@RestController()
@Transactional
public class DeskController {

	@Autowired
	private DeskService deskService;

	/**
	 * 创建
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("desk")
	public ResultPojo createDesk(@RequestBody TbDeskEntity entity) {

		int result = deskService.createDesk(entity);

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
	@PatchMapping("desk")
	public ResultPojo modifyDesk(@RequestBody TbDeskEntity entity) {
		int updateResult = deskService.updateDesk(entity);

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
	@GetMapping("desk/id/{id}")
	public ResultPojo getDeskById(@PathVariable Long id) {
		TbDeskEntity result = deskService.getDeskById(id);

		// 查询成功
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			// 查询失败
			return new ResultPojo(Common.ERR, null);
		}
	}
	
	/**
	 * 查询指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("desk/state/{state}")
	public ResultPojo getDeskByState(@PathVariable byte state) {
		List<TbDeskEntity> result = deskService.getdeskByState(state);

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
	@DeleteMapping("desk/id/{id}")
	public ResultPojo deleteDesk(@PathVariable Long id) {
		int deleteResult = deskService.deleteDeskById(id);

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
	@GetMapping("desk/page/{page}")
	public ResultPojo getDeskListByPage(@PathVariable int page) {
		List<TbDeskEntity> deskList = deskService.getDeskListByPage(page);

		// 查询成功
		if (deskList == null || deskList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, deskList);
		}
	}

	/**
	 * 获取所有数据
	 * @return
	 */
	@GetMapping("desk/count")
	public ResultPojo getDeskCount() {
		return new ResultPojo(Common.OK, deskService.getDeskCount());
	}

	/**
	 * 模糊查询
	 * @param searchTxt
	 * @param pageNum
	 * @return
	 */
	@GetMapping("desk/search/{search}/page/{page}")
	public ResultPojo getDeskListBySearchAndPage(@PathVariable("search") String search, @PathVariable("page") int page) {
		return new ResultPojo(Common.OK, deskService.getDeskListBySearchAndPage(search, page));
	}
	
	/**
	 * 获取符合检索条件的所有数据数量
	 * @param searchTxt
	 * @return
	 */
	@GetMapping("desk/search/{search}/count")
	public ResultPojo getDeskCountBySearch(@PathVariable String search) {
		return new ResultPojo(Common.OK, deskService.getDeskCountBySearch(search));
	}
	
	/**
	 * 获取所有台号
	 * @param 
	 * @return
	 */
	@GetMapping("deskList")
	public ResultPojo getDeskList() {
		return new ResultPojo(Common.OK, deskService.getDeskList());
	}
}
