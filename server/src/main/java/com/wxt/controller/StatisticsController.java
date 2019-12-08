package com.wxt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxt.pojo.Common;
import com.wxt.pojo.ResultPojo;
import com.wxt.service.StatisticsService;

@RestController
public class StatisticsController {

	@Autowired
	private StatisticsService statisticsService;
	
	/**
	 * 获取年数据
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("statistics/year")
	public ResultPojo getYear() {
		return new ResultPojo(Common.OK, statisticsService.getYear());
	}
	
	/**
	 * 获取月数据
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("statistics/month")
	public ResultPojo getMonth() {
		return new ResultPojo(Common.OK, statisticsService.getMonth());
	}
	
	/**
	 * 获取日数据
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("statistics/day")
	public ResultPojo getDay() {
		return new ResultPojo(Common.OK, statisticsService.getDay());
	}
	
	
}
