package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.Common;
import com.example.pojo.ResultPojo;

@RestController
public class HelloController {

	@GetMapping("hello")
	public ResultPojo hello() {
		return new ResultPojo(Common.Http.success, "hello", null);
	}
}
