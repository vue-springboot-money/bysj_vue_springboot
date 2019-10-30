package com.ttcanteen.service;

import com.ttcanteen.entity.TbNewsEntity;

public interface NewsService {

	/**
	 * 创建一条新闻
	 * @param entity
	 * @return
	 */
	TbNewsEntity createNews(TbNewsEntity entity);

}
