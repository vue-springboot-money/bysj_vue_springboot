package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.LoginDto;
import com.example.entity.TbUser;
import com.example.pojo.Common;
import com.example.pojo.ResultPojo;
import com.example.service.LoginService;

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
		TbUser result = loginService.login(user);
		
		if (result == null) {
			return new ResultPojo(Common.Http.SUCCESS, "用户名/密码错误", user);
		} else {
			return new ResultPojo(Common.Http.SUCCESS, "登陆成功", result);
		}
	}
}
