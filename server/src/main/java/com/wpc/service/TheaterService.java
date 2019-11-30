package com.wpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wpc.entity.TbTheaterEntity;
import com.wpc.mapper.TbTheaterMapper;

@Service
public class TheaterService {

	@Autowired
	private TbTheaterMapper tbTheaterMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建剧场
	 * 
	 * @param entity
	 * @return
	 */
	public int createTheater(TbTheaterEntity entity) {
		return tbTheaterMapper.insert(entity);
	}

	/**
	 * 更新剧场信息
	 * 
	 * @param entity
	 * @return
	 */
	public int updateTheater(TbTheaterEntity entity) {
		return tbTheaterMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的剧场
	 * 
	 * @param id
	 * @return
	 */
	public TbTheaterEntity getTheaterById(Long id) {
		return tbTheaterMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的剧场
	 * 
	 * @param id
	 * @return
	 */
	public int deleteTheaterById(Long id) {
		return tbTheaterMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<TbTheaterEntity> getTheaterListByPage(int page) {
		return tbTheaterMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 查询剧场总数
	 * 
	 * @return
	 */
	public int getTheaterCount() {
		return tbTheaterMapper.selectCount();
	}

	/**
	 * 模糊查询
	 * @param search
	 * @param page
	 * @return
	 */
	public List<TbTheaterEntity> getTheaterListBySearchAndPage(String search, int page) {
		return tbTheaterMapper.selectBySearchAndPage(search, (page - 1) * count, count);
	}

	/**
	 * 检索的剧场总数
	 * @param search
	 * @return
	 */
	public int getTheaterCountBySearch(String search) {
		return tbTheaterMapper.selectCountBySearch(search);
	}

	/**
	 * 获取所有剧场
	 * @param search
	 * @return
	 */
	public List<TbTheaterEntity> getTheaterList() {
		return tbTheaterMapper.selectAll();
	}
}
