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

import com.wpc.entity.TbTeamEntity;
import com.wpc.pojo.Common;
import com.wpc.pojo.ResultPojo;
import com.wpc.service.TeamService;

@RestController()
@Transactional
public class TeamController {

	@Autowired
	private TeamService teamService;

	/**
	 * 创建队伍
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("team")
	public ResultPojo createTeam(@RequestBody TbTeamEntity entity) {

		int result = teamService.createTeam(entity);

		// 注册失败
		if (result != 1) {
			return new ResultPojo(Common.ERR, entity);
		} else {
			// 注册成功
			return new ResultPojo(Common.OK, entity);
		}
	}

	/**
	 * 更新队伍
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("team")
	public ResultPojo modifyTeam(@RequestBody TbTeamEntity entity) {
		int updateResult = teamService.updateTeam(entity);

		// 更新成功
		if (updateResult != 0) {
			return new ResultPojo(Common.OK, entity);
		} else {
			// 更新失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 查询指定id的队伍
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("team/{id}")
	public ResultPojo getTeamById(@PathVariable Long id) {
		TbTeamEntity result = teamService.getTeamById(id);

		// 查询成功
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			// 查询失败
			return new ResultPojo(Common.ERR, null);
		}
	}
	
	/**
	 * 删除指定id的队伍
	 * @param id
	 * @return
	 */
	@DeleteMapping("team/{id}")
	public ResultPojo deleteTeam(@PathVariable Long id) {
		int deleteResult = teamService.deleteTeamById(id);

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
	@GetMapping("team/page/{page}")
	public ResultPojo getTeamListByPage(@PathVariable int page) {
		List<TbTeamEntity> teamList = teamService.getTeamListByPage(page);

		// 查询成功
		if (teamList == null || teamList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, teamList);
		}
	}

	/**
	 * 获取所有队伍数量
	 * @return
	 */
	@GetMapping("team/count")
	public ResultPojo getTeamCount() {
		return new ResultPojo(Common.OK, teamService.getTeamCount());
	}

	/**
	 * 模糊查询
	 * @param searchTxt
	 * @param pageNum
	 * @return
	 */
	@GetMapping("team/search/{search}/page/{page}")
	public ResultPojo getTeamListBySearchAndPage(@PathVariable("search") String search, @PathVariable("page") int page) {
		return new ResultPojo(Common.OK, teamService.getTeamListBySearchAndPage(search, page));
	}
	
	/**
	 * 获取符合检索条件的所有队伍数量
	 * @param searchTxt
	 * @return
	 */
	@GetMapping("team/search/{search}/count")
	public ResultPojo getTeamCountBySearch(@PathVariable String search) {
		return new ResultPojo(Common.OK, teamService.getTeamCountBySearch(search));
	}
}
