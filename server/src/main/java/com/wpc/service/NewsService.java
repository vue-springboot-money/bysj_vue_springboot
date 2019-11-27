package com.wpc.service;

import java.util.List;

import com.wpc.entity.TbNewsEntity;

public interface NewsService {

	/**
	 * 创建一条新闻
	 * @param entity
	 * @return
	 */
	TbNewsEntity createNews(TbNewsEntity entity);

	/**
	 * 删除新闻
	 * @param id
	 * @return
	 */
	int deleteNews(Long id);

	/**
	 * 更新新闻
	 * @param entity
	 * @return
	 */
	TbNewsEntity updateNews(TbNewsEntity entity);

	/**
	 * 根据id查询新闻
	 * @param id
	 * @return
	 */
	TbNewsEntity selectNewsById(Long id);

	/**
	 * 分页查询新闻
	 * @param pageNum
	 * @return
	 */
	List<TbNewsEntity> selectNewsByPage(int pageNum);

	/**
	 * 获取数据总数
	 * @return
	 */
	int selectSum();

}
