package com.gx.controller;

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

import com.gx.entity.TbUserEntity;
import com.gx.pojo.Common;
import com.gx.pojo.ResultPojo;
import com.gx.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController()
@Api(description = "用户相关接口")
@Transactional
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 注册/创建用户
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("user")
	@ApiOperation("创建用户")
	public ResultPojo createUser(@RequestBody TbUserEntity entity) {

		TbUserEntity result = userService.createUser(entity);

		// 注册失败
		if (result == null) {
			return new ResultPojo(Common.ERR, result);
		} else {
			// 注册成功
			return new ResultPojo(Common.OK, result);
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("userPwd")
	@ApiOperation("修改密码")
	public ResultPojo changePwd(@RequestBody TbUserEntity entity) {
		int result = userService.changePwd(entity);

		// 修改成功
		if (result == 1) {
			return new ResultPojo(Common.OK, null);
		} else {
			// 修改失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 获取用户信息
	 * 
	 * @param entity
	 * @return
	 */
	@GetMapping("user/{id}")
	@ApiOperation("获取指定id的用户信息")
	public ResultPojo getUserById(@PathVariable Long id) {
		TbUserEntity result = userService.findUserById(id);

		// 查询成功
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			// 查询失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 修改用户信息
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("user")
	@ApiOperation("更新用户信息")
	public ResultPojo modifyUser(@RequestBody TbUserEntity entity) {
		TbUserEntity updateResult = userService.updateUser(entity);

		// 更新成功
		if (updateResult != null) {
			return new ResultPojo(Common.OK, updateResult);
		} else {
			// 更新失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 充值
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("recharge")
	@ApiOperation("充值")
	public ResultPojo recharge(@RequestBody TbUserEntity entity) {
		TbUserEntity rechargeResult = userService.recharge(entity);

		// 充值成功
		if (rechargeResult != null) {
			return new ResultPojo(Common.OK, rechargeResult);
		} else {
			// 充值失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 消费
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("consume")
	@ApiOperation("消费")
	public ResultPojo consume(@RequestBody TbUserEntity entity) {
		TbUserEntity consumeResult = userService.consume(entity);

		// 消费成功
		if (consumeResult != null) {
			return new ResultPojo(Common.OK, consumeResult);
		} else {
			// 消费失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	@DeleteMapping("user/{id}")
	@ApiOperation("删除用户")
	public ResultPojo deleteUser(@PathVariable Long id) {
		int deleteResult = userService.deleteUser(id);

		// 删除成功
		if (deleteResult == 1) {
			return new ResultPojo(Common.OK, deleteResult);
		} else {
			// 删除失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	@GetMapping("users/{pageNum}")
	@ApiOperation("分页查询用户")
	public ResultPojo getUserListByPage(@PathVariable int pageNum) {
		List<TbUserEntity> userList = userService.selectUserListByPage(pageNum);

		// 查询成功
		if (userList == null || userList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, userList);
		}
	}

	@GetMapping("userTotal")
	@ApiOperation("查询用户总数")
	public ResultPojo getUserTotal() {
		return new ResultPojo(Common.OK, userService.selectUserTotal());
	}

	@GetMapping("user/search/{searchTxt}/pageNum/{pageNum}")
	@ApiOperation("模糊查询")
	public ResultPojo searchUser(@PathVariable("searchTxt") String searchTxt, @PathVariable("pageNum") int pageNum) {
		return new ResultPojo(Common.OK, userService.searchUserByPage(searchTxt, pageNum));
	}
	
	@GetMapping("searchUserTotal/{searchTxt}")
	@ApiOperation("模糊查询用户总数")
	public ResultPojo searchUserTotal(@PathVariable String searchTxt) {
		return new ResultPojo(Common.OK, userService.selectSearchUserTotal(searchTxt));
	}
}
