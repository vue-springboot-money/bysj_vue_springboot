package com.wxt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wxt.dto.LoginDto;
import com.wxt.entity.TbUserEntity;
import com.wxt.pojo.Common;
import com.wxt.pojo.ResultPojo;
import com.wxt.service.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "登录接口")
@Transactional
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@PostMapping("login")
	@ApiOperation("登录接口")
	public ResultPojo login(@RequestBody LoginDto user) {
		TbUserEntity result = loginService.login(user);
		
		if (result == null) {
			return new ResultPojo(Common.ERR, null);
		} else {
			return new ResultPojo(Common.OK, user.getUserName());
		}
	}
}
