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

import com.wpc.entity.TbActorEntity;
import com.wpc.pojo.Common;
import com.wpc.pojo.ResultPojo;
import com.wpc.service.ActorService;

@RestController()
@Transactional
public class ActorController {

	@Autowired
	private ActorService actorService;

	/**
	 * 创建演员
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("actor")
	public ResultPojo createActor(@RequestBody TbActorEntity entity) {

		int result = actorService.createActor(entity);

		// 注册失败
		if (result != 1) {
			return new ResultPojo(Common.ERR, entity);
		} else {
			// 注册成功
			return new ResultPojo(Common.OK, entity);
		}
	}

	/**
	 * 更新演员
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("actor")
	public ResultPojo modifyActor(@RequestBody TbActorEntity entity) {
		int updateResult = actorService.updateActor(entity);

		// 更新成功
		if (updateResult != 0) {
			return new ResultPojo(Common.OK, entity);
		} else {
			// 更新失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 查询指定id的演员
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("actor/{id}")
	public ResultPojo getActorById(@PathVariable Long id) {
		TbActorEntity result = actorService.getActorById(id);

		// 查询成功
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			// 查询失败
			return new ResultPojo(Common.ERR, null);
		}
	}
	
	/**
	 * 删除指定id的演员
	 * @param id
	 * @return
	 */
	@DeleteMapping("actor/{id}")
	public ResultPojo deleteActor(@PathVariable Long id) {
		int deleteResult = actorService.deleteActorById(id);

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
	@GetMapping("actor/page/{page}")
	public ResultPojo getActorListByPage(@PathVariable int page) {
		List<TbActorEntity> actorList = actorService.getActorListByPage(page);

		// 查询成功
		if (actorList == null || actorList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, actorList);
		}
	}

	/**
	 * 获取所有演员数量
	 * @return
	 */
	@GetMapping("actor/count")
	public ResultPojo getActorCount() {
		return new ResultPojo(Common.OK, actorService.getActorCount());
	}

	/**
	 * 模糊查询
	 * @param searchTxt
	 * @param pageNum
	 * @return
	 */
	@GetMapping("actor/search/{search}/page/{page}")
	public ResultPojo getActorListBySearchAndPage(@PathVariable("search") String search, @PathVariable("page") int page) {
		return new ResultPojo(Common.OK, actorService.getActorListBySearchAndPage(search, page));
	}
	
	/**
	 * 获取符合检索条件的所有演员数量
	 * @param searchTxt
	 * @return
	 */
	@GetMapping("actor/search/{search}/count")
	public ResultPojo getActorCountBySearch(@PathVariable String search) {
		return new ResultPojo(Common.OK, actorService.getActorCountBySearch(search));
	}
}
