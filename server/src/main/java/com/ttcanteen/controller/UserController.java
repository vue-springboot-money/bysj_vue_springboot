package com.ttcanteen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttcanteen.entity.TbUserEntity;
import com.ttcanteen.pojo.Common;
import com.ttcanteen.pojo.ResultPojo;
import com.ttcanteen.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController()
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
	public ResultPojo add(@ModelAttribute TbUserEntity entity) {
		
		TbUserEntity result = userService.createUser(entity);

		// 注册失败
		if (result == null) {
			return new ResultPojo(Common.ERR, result);
		} else {
			// 注册成功
			return new ResultPojo(Common.OK, result);
		}
	}
}
