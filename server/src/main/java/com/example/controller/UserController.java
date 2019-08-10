package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.TbUser;
import com.example.pojo.Common;
import com.example.pojo.ResultPojo;
import com.example.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController()
@Slf4j
@Api(description = "用户相关接口")
@Transactional
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 创建用户
	 * 
	 * @param tbUser
	 * @return
	 */
	@PostMapping("user")
	@ApiOperation("创建用户")
	public ResultPojo add(@ModelAttribute TbUser tbUser) {

		TbUser result = userService.createUser(tbUser);

		if (result == null) {
			return new ResultPojo(Common.Http.SUCCESS, "创建用户失败", tbUser);
		} else {
			return new ResultPojo(Common.Http.SUCCESS, "创建用户成功", result);
		}
	}

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	@GetMapping("user")
	@ApiOperation("查询所有用户")
	public ResultPojo findAllUser() {
		List<TbUser> result = userService.findAllUser();
		return new ResultPojo(Common.Http.SUCCESS, "查询成功", result);
	}

	/**
	 * 根据id查询用户
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("user/id/{id}")
	@ApiOperation("根据id查询用户")
	public ResultPojo findUserById(@PathVariable("id") Long id) {
		TbUser result = userService.findUserById(id);
		return new ResultPojo(Common.Http.SUCCESS, "查询成功", result);
	}

	/**
	 * 根据username查询用户
	 * 
	 * @param username
	 * @return
	 */
	@GetMapping("user/username/{username}")
	@ApiOperation("根据username查询用户")
	public ResultPojo findUserByUsername(@PathVariable("username") String username) {
		List<TbUser> result = userService.findUserByUsername(username);
		return new ResultPojo(Common.Http.SUCCESS, "查询成功", result);
	}

	/**
	 * 根据type查询用户
	 * 
	 * @param type
	 * @return
	 */
	@GetMapping("user/type/{type}")
	@ApiOperation("根据type查询用户")
	public ResultPojo findUserByType(@PathVariable("type") byte type) {
		List<TbUser> result = userService.findUserByType(type);
		return new ResultPojo(Common.Http.SUCCESS, "查询成功", result);
	}

	/**
	 * 根据id删除用户
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("user/id/{id}")
	@ApiOperation("根据id删除用户")
	public ResultPojo deleteUserById(@PathVariable("id") Long id) {
		int result = userService.removeUser(id);

		if (result == 1) {
			return new ResultPojo(Common.Http.SUCCESS, "删除成功", id);
		} else {
			return new ResultPojo(Common.Http.SUCCESS, "删除失败", id);
		}
	}

}
