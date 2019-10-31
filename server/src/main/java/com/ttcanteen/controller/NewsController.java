package com.ttcanteen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttcanteen.entity.TbNewsEntity;
import com.ttcanteen.pojo.Common;
import com.ttcanteen.pojo.ResultPojo;
import com.ttcanteen.service.NewsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "新闻相关接口")
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

	@DeleteMapping("news/{id}")
	@ApiOperation("删除新闻")
	public ResultPojo deleteNews(@PathVariable Long id) {
		int deleteResult = newsService.deleteNews(id);

		if (deleteResult == 1) {
			return new ResultPojo(Common.OK, id);
		} else {
			return new ResultPojo(Common.ERR, id);
		}
	}

	@PatchMapping("news")
	@ApiOperation("更新新闻")
	public ResultPojo updateNews(@ModelAttribute TbNewsEntity entity) {
		TbNewsEntity updateResult = newsService.updateNews(entity);

		// 如果更新成功，返回更新后的数据
		if (updateResult != null) {
			return new ResultPojo(Common.OK, updateResult);
		} else {
			// 更新不成功，返回请求数据
			return new ResultPojo(Common.ERR, entity);
		}
	}

	@GetMapping("news/{id}")
	@ApiOperation("获取指定id的新闻")
	public ResultPojo getNewsById(Long id) {
		TbNewsEntity selectResult = newsService.selectNewsById(id);

		// 查询成功
		if (selectResult != null) {
			return new ResultPojo(Common.OK, selectResult);
		} else {
			return new ResultPojo(Common.ERR, selectResult);
		}
	}
	
	@GetMapping("newsList/{pageNum}")
	@ApiOperation("分页查询新闻")
	public ResultPojo getNewsByPage(@PathVariable int pageNum) {
		List<TbNewsEntity> selectResult = newsService.selectNewsByPage(pageNum);

		// 查询成功
		if (selectResult != null) {
			return new ResultPojo(Common.OK, selectResult);
		} else {
			return new ResultPojo(Common.ERR, selectResult);
		}
	}
	
	
}
