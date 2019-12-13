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

import com.wpc.dto.ProgramDto;
import com.wpc.entity.TbProgramEntity;
import com.wpc.pojo.Common;
import com.wpc.pojo.ResultPojo;
import com.wpc.service.ProgramService;

@RestController()
@Transactional
public class ProgramController {

	@Autowired
	private ProgramService programService;

	/**
	 * 创建节目单
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("program")
	public ResultPojo createProgram(@RequestBody TbProgramEntity entity) {

		int result = programService.createProgram(entity);

		// 注册失败
		if (result != 1) {
			return new ResultPojo(Common.ERR, entity);
		} else {
			// 注册成功
			return new ResultPojo(Common.OK, entity);
		}
	}

	/**
	 * 更新节目单
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("program")
	public ResultPojo modifyProgram(@RequestBody TbProgramEntity entity) {
		int updateResult = programService.updateProgram(entity);

		// 更新成功
		if (updateResult != 0) {
			return new ResultPojo(Common.OK, entity);
		} else {
			// 更新失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 查询指定id的节目单
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("program/id/{id}")
	public ResultPojo getProgramById(@PathVariable Long id) {
		ProgramDto result = programService.getProgramById(id);

		// 查询成功
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			// 查询失败
			return new ResultPojo(Common.ERR, null);
		}
	}
	
	/**
	 * 删除指定id的节目单
	 * @param id
	 * @return
	 */
	@DeleteMapping("program/id/{id}")
	public ResultPojo deleteProgram(@PathVariable Long id) {
		int deleteResult = programService.deleteProgramById(id);

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
	@GetMapping("program/page/{page}")
	public ResultPojo getProgramListByPage(@PathVariable int page) {
		List<ProgramDto> programList = programService.getProgramListByPage(page);

		// 查询成功
		if (programList == null || programList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, programList);
		}
	}

	/**
	 * 获取所有节目单数量
	 * @return
	 */
	@GetMapping("program/count")
	public ResultPojo getProgramCount() {
		return new ResultPojo(Common.OK, programService.getProgramCount());
	}

	/**
	 * 模糊查询
	 * @param search
	 * @param page
	 * @return
	 */
	@GetMapping("program/search/{search}/page/{page}")
	public ResultPojo getProgramListBySearchAndPage(@PathVariable("search") String search, @PathVariable("page") int page) {
		return new ResultPojo(Common.OK, programService.getProgramListBySearchAndPage(search, page));
	}
	
	/**
	 * 获取符合检索条件的所有节目单数量
	 * @param searchTxt
	 * @return
	 */
	@GetMapping("program/search/{search}/count")
	public ResultPojo getProgramCountBySearch(@PathVariable String search) {
		return new ResultPojo(Common.OK, programService.getProgramCountBySearch(search));
	}
	
	/**
	 * 获取符合检索条件的所有节目单数量
	 * @param searchTxt
	 * @return
	 */
	@GetMapping("program/ticket")
	public ResultPojo getProgramForTicket() {
		return new ResultPojo(Common.OK, programService.getProgramForTicket());
	}
}
