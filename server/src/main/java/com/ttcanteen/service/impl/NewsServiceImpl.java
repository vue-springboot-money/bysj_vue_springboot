package com.ttcanteen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ttcanteen.entity.TbNewsEntity;
import com.ttcanteen.mapper.TbNewsMapper;
import com.ttcanteen.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	@Value("${default.uploadPath}")
	private String uploadPath;

	@Autowired
	private TbNewsMapper tbNewsMapper;

	@Override
	public TbNewsEntity createNews(TbNewsEntity entity) {
		// 初始化数据
		if (entity.getImg() != null) {
			entity.setImg(uploadPath + entity.getImg());
		} else {
			entity.setImg("");
		}
		
		// 设置为未发布状态
		entity.setState((byte) 0);

		// 插入到数据库
		int insertResult = tbNewsMapper.insert(entity);

		// 插入成功，返回插入后的数据
		if (insertResult == 1) {
			return tbNewsMapper.selectByTitleAndAuthor(entity).get(0);
		} else {
			// 插入失败，返回空
			return null;
		}
	}

	@Override
	public int deleteNews(Long id) {
		return tbNewsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TbNewsEntity updateNews(TbNewsEntity entity) {
		int updateResult = tbNewsMapper.updateByPrimaryKey(entity);
		TbNewsEntity updateNewsEntity = null;
		
		if (updateResult == 1) {
			updateNewsEntity = tbNewsMapper.selectByPrimaryKey(entity.getId());
		} else {
			updateNewsEntity = null;
		}
		return updateNewsEntity;
	}

	@Override
	public TbNewsEntity selectNewsById(Long id) {
		return tbNewsMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TbNewsEntity> selectNewsByPage(int pageNum) {
		int count = 10;
		
		return tbNewsMapper.selectByPage((pageNum - 1) * count, count);
	}

	@Override
	public int selectSum() {
		return tbNewsMapper.selectCount();
	}

}
