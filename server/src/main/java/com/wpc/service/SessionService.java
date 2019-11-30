package com.wpc.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wpc.entity.TbSessionEntity;
import com.wpc.mapper.TbSessionMapper;

@Service
public class SessionService {

	@Autowired
	private TbSessionMapper tbSessionMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建专场
	 * 
	 * @param entity
	 * @return
	 */
	public int createSession(TbSessionEntity entity) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(entity.getDate());
		calendar.add(Calendar.DATE, 1);
		entity.setDate(calendar.getTime());
		return tbSessionMapper.insert(entity);
	}

	/**
	 * 更新专场信息
	 * 
	 * @param entity
	 * @return
	 */
	public int updateSession(TbSessionEntity entity) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(entity.getDate());
		calendar.add(Calendar.DATE, 1);
		entity.setDate(calendar.getTime());
		return tbSessionMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的专场
	 * 
	 * @param id
	 * @return
	 */
	public TbSessionEntity getSessionById(Long id) {
		return tbSessionMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的专场
	 * 
	 * @param id
	 * @return
	 */
	public int deleteSessionById(Long id) {
		return tbSessionMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @return
	 */
	public List<TbSessionEntity> getSessionListByPage(int page) {
		return tbSessionMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 查询专场总数
	 * 
	 * @return
	 */
	public int getSessionCount() {
		return tbSessionMapper.selectCount();
	}
}
