package com.wpc.controller;

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

import com.wpc.entity.TbTheaterEntity;
import com.wpc.pojo.Common;
import com.wpc.pojo.ResultPojo;
import com.wpc.service.TheaterService;

@RestController()
@Transactional
public class TheaterController {

	@Autowired
	private TheaterService theaterService;

	/**
	 * 创建剧场
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("theater")
	public ResultPojo createTheater(@RequestBody TbTheaterEntity entity) {

		int result = theaterService.createTheater(entity);

		// 注册失败
		if (result != 1) {
			return new ResultPojo(Common.ERR, entity);
		} else {
			// 注册成功
			return new ResultPojo(Common.OK, entity);
		}
	}

	/**
	 * 更新剧场
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("theater")
	public ResultPojo modifyTheater(@RequestBody TbTheaterEntity entity) {
		int updateResult = theaterService.updateTheater(entity);

		// 更新成功
		if (updateResult != 0) {
			return new ResultPojo(Common.OK, entity);
		} else {
			// 更新失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 查询指定id的剧场
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("theater/{id}")
	public ResultPojo getTheaterById(@PathVariable Long id) {
		TbTheaterEntity result = theaterService.getTheaterById(id);

		// 查询成功
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			// 查询失败
			return new ResultPojo(Common.ERR, null);
		}
	}
	
	/**
	 * 删除指定id的剧场
	 * @param id
	 * @return
	 */
	@DeleteMapping("theater/{id}")
	public ResultPojo deleteTheater(@PathVariable Long id) {
		int deleteResult = theaterService.deleteTheaterById(id);

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
	@GetMapping("theater/page/{page}")
	public ResultPojo getTheaterListByPage(@PathVariable int page) {
		List<TbTheaterEntity> theaterList = theaterService.getTheaterListByPage(page);

		// 查询成功
		if (theaterList == null || theaterList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, theaterList);
		}
	}

	/**
	 * 获取所有剧场数量
	 * @return
	 */
	@GetMapping("theater/count")
	public ResultPojo getTheaterCount() {
		return new ResultPojo(Common.OK, theaterService.getTheaterCount());
	}

	/**
	 * 模糊查询
	 * @param searchTxt
	 * @param pageNum
	 * @return
	 */
	@GetMapping("theater/search/{search}/page/{page}")
	public ResultPojo getTheaterListBySearchAndPage(@PathVariable("search") String search, @PathVariable("page") int page) {
		return new ResultPojo(Common.OK, theaterService.getTheaterListBySearchAndPage(search, page));
	}
	
	/**
	 * 获取符合检索条件的所有剧场数量
	 * @param searchTxt
	 * @return
	 */
	@GetMapping("theater/search/{search}/count")
	public ResultPojo getTheaterCountBySearch(@PathVariable String search) {
		return new ResultPojo(Common.OK, theaterService.getTheaterCountBySearch(search));
	}
}
