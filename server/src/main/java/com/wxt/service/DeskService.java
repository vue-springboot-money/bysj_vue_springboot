package com.wxt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wxt.entity.TbDeskEntity;
import com.wxt.mapper.TbDeskMapper;

@Service
public class DeskService {

	@Autowired
	private TbDeskMapper tbDeskMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建
	 * 
	 * @param entity
	 * @return
	 */
	public int createDesk(TbDeskEntity entity) {
		return tbDeskMapper.insert(entity);
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 */
	public int updateDesk(TbDeskEntity entity) {
		return tbDeskMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	public TbDeskEntity getDeskById(Long id) {
		return tbDeskMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	public int deleteDeskById(Long id) {
		return tbDeskMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<TbDeskEntity> getDeskListByPage(int page) {
		return tbDeskMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 查询数据总数
	 * 
	 * @return
	 */
	public int getDeskCount() {
		return tbDeskMapper.selectCount();
	}

	/**
	 * 模糊查询
	 * @param search
	 * @param page
	 * @return
	 */
	public List<TbDeskEntity> getDeskListBySearchAndPage(String search, int page) {
		return tbDeskMapper.selectBySearchAndPage(search, (page - 1) * count, count);
	}

	/**
	 * 检索的数据总数
	 * @param search
	 * @return
	 */
	public int getDeskCountBySearch(String search) {
		return tbDeskMapper.selectCountBySearch(search);
	}

	/**
	 * 获取所有台号
	 * @return
	 */
	public List<TbDeskEntity> getDeskList() {
		return tbDeskMapper.selectAll();
	}
}
