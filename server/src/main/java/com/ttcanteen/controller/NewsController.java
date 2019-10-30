package com.ttcanteen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttcanteen.entity.TbNewsEntity;
import com.ttcanteen.pojo.Common;
import com.ttcanteen.pojo.ResultPojo;
import com.ttcanteen.service.NewsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "菜单相关接口")
@Transactional
public class NewsController {
	
	@Autowired
	private NewsService newsService;

	@PostMapping("news")
	@ApiOperation("创建新闻")
	public ResultPojo createNews(@ModelAttribute TbNewsEntity entity) {
		TbNewsEntity result = newsService.createNews(entity);
		
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			return new ResultPojo(Common.ERR, entity);
		}
	}
}
