package com.wpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wpc.entity.TbNewsEntity;
import com.wpc.mapper.TbNewsMapper;

@Service
public class NewsService {

	@Autowired
	private TbNewsMapper tbNewsMapper;
	
	@Value("${default.count}")
	private int count;
	
	/**
	 * 创建新闻
	 * @param entity
	 * @return
	 */
	public int createNews(TbNewsEntity entity) {
		return tbNewsMapper.insert(entity);
	}

	/**
	 * 更新新闻
	 * @param entity
	 * @return
	 */
	public int updateNews(TbNewsEntity entity) {
		return tbNewsMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的新闻
	 * @param id
	 * @return
	 */
	public TbNewsEntity getNewsById(Long id) {
		return tbNewsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的新闻
	 * @param id
	 * @return
	 */
	public int deleteNewsById(Long id) {
		return tbNewsMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<TbNewsEntity> getNewsListByPage(int page) {
		return tbNewsMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 获取所有新闻数量
	 * @return
	 */
	public int getNewsCount() {
		return tbNewsMapper.selectCount();
	}

}
