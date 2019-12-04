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

import com.wxt.entity.TbUserEntity;
import com.wxt.pojo.Common;
import com.wxt.pojo.ResultPojo;
import com.wxt.service.UserService;

@RestController()
@Transactional
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 创建
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("user")
	public ResultPojo createUser(@RequestBody TbUserEntity entity) {

		int result = userService.createUser(entity);

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
	@PatchMapping("user")
	public ResultPojo modifyUser(@RequestBody TbUserEntity entity) {
		int updateResult = userService.updateUser(entity);

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
	@GetMapping("user/id/{id}")
	public ResultPojo getUserById(@PathVariable Long id) {
		TbUserEntity result = userService.getUserById(id);

		// 查询成功
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			// 查询失败
			return new ResultPojo(Common.ERR, null);
		}
	}
	
	/**
	 * 查询指定用户名的数据
	 * 
	 * @param username
	 * @return
	 */
	@GetMapping("user/username/{username}")
	public ResultPojo getUserByUserna(@PathVariable String username) {
		TbUserEntity result = userService.getUserByUsername(username);

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
	@DeleteMapping("user/id/{id}")
	public ResultPojo deleteUser(@PathVariable Long id) {
		int deleteResult = userService.deleteUserById(id);

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
	@GetMapping("user/page/{page}")
	public ResultPojo getUserListByPage(@PathVariable int page) {
		List<TbUserEntity> userList = userService.getUserListByPage(page);

		// 查询成功
		if (userList == null || userList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, userList);
		}
	}

	/**
	 * 获取所有数据
	 * @return
	 */
	@GetMapping("user/count")
	public ResultPojo getUserCount() {
		return new ResultPojo(Common.OK, userService.getUserCount());
	}

	/**
	 * 模糊查询
	 * @param searchTxt
	 * @param pageNum
	 * @return
	 */
	@GetMapping("user/search/{search}/page/{page}")
	public ResultPojo getUserListBySearchAndPage(@PathVariable("search") String search, @PathVariable("page") int page) {
		return new ResultPojo(Common.OK, userService.getUserListBySearchAndPage(search, page));
	}
	
	/**
	 * 获取符合检索条件的所有数据数量
	 * @param searchTxt
	 * @return
	 */
	@GetMapping("user/search/{search}/count")
	public ResultPojo getUserCountBySearch(@PathVariable String search) {
		return new ResultPojo(Common.OK, userService.getUserCountBySearch(search));
	}
}
