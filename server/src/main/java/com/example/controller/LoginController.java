package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.LoginDto;
import com.example.entity.TbUser;
import com.example.pojo.Common;
import com.example.pojo.ResultPojo;
import com.example.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@PostMapping("login")
	public ResultPojo login(LoginDto user) {
		TbUser result = loginService.login(user);
		
		if (result == null) {
			return new ResultPojo(Common.Http.SUCCESS, "用户名/密码错误", user);
		} else {
			return new ResultPojo(Common.Http.SUCCESS, "登陆成功", result);
		}
	}
}
