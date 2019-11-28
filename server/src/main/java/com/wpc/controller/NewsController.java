package com.wpc.controller;

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

import com.wpc.entity.TbNewsEntity;
import com.wpc.pojo.Common;
import com.wpc.pojo.ResultPojo;
import com.wpc.service.NewsService;

@RestController
@Transactional
public class NewsController {

	@Autowired
	private NewsService newsService;

	/**
	 * 创建新闻
	 * @param entity
	 * @return
	 */
	@PostMapping("news")
	public ResultPojo createNews(@RequestBody TbNewsEntity entity) {
		int result = newsService.createNews(entity);

		if (result != 0) {
			return new ResultPojo(Common.OK, entity);
		} else {
			return new ResultPojo(Common.ERR, entity);
		}
	}
	
	/**
	 * 更新新闻
	 * @param entity
	 * @return
	 */
	@PatchMapping("news")
	public ResultPojo updateNews(@RequestBody TbNewsEntity entity) {
		int updateResult = newsService.updateNews(entity);

		// 如果更新成功，返回更新后的数据
		if (updateResult != 0) {
			return new ResultPojo(Common.OK, entity);
		} else {
			// 更新不成功，返回请求数据
			return new ResultPojo(Common.ERR, entity);
		}
	}
	
	/**
	 * 查询指定id的新闻
	 * @param id
	 * @return
	 */
	@GetMapping("news/id/{id}")
	public ResultPojo getNewsById(@PathVariable Long id) {
		TbNewsEntity selectResult = newsService.getNewsById(id);

		// 查询成功
		if (selectResult != null) {
			return new ResultPojo(Common.OK, selectResult);
		} else {
			return new ResultPojo(Common.ERR, selectResult);
		}
	}

	/**
	 * 删除指定id的新闻
	 * @param id
	 * @return
	 */
	@DeleteMapping("news/id/{id}")
	public ResultPojo deleteNewsById(@PathVariable Long id) {
		int deleteResult = newsService.deleteNewsById(id);

		if (deleteResult == 1) {
			return new ResultPojo(Common.OK, id);
		} else {
			return new ResultPojo(Common.ERR, id);
		}
	}
	
	/**
	 * 分页查询
	 * @param pageNum
	 * @return
	 */
	@GetMapping("news/page/{page}")
	public ResultPojo getNewsListByPage(@PathVariable int page) {
		List<TbNewsEntity> selectResult = newsService.getNewsListByPage(page);

		// 查询成功
		if (selectResult != null) {
			return new ResultPojo(Common.OK, selectResult);
		} else {
			return new ResultPojo(Common.ERR, selectResult);
		}
	}

	/**
	 * 获取所有新闻数量
	 * @return
	 */
	@GetMapping("news/count")
	public ResultPojo getNewsCount() {
		int newsSum = newsService.getNewsCount();

		return new ResultPojo(Common.OK, newsSum);
	}
	
	
}
