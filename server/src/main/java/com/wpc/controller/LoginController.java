package com.wpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wpc.dto.LoginDto;
import com.wpc.entity.TbUserEntity;
import com.wpc.pojo.Common;
import com.wpc.pojo.ResultPojo;
import com.wpc.service.LoginService;

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
			return new ResultPojo(Common.ERR, user);
		} else {
			return new ResultPojo(Common.OK, result);
		}
	}
}
