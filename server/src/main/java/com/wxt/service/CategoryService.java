package com.wxt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wxt.entity.TbCategoryEntity;
import com.wxt.mapper.TbCategoryMapper;

@Service
public class CategoryService {

	@Autowired
	private TbCategoryMapper tbCategoryMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建
	 * 
	 * @param entity
	 * @return
	 */
	public int createCategory(TbCategoryEntity entity) {
		return tbCategoryMapper.insert(entity);
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 */
	public int updateCategory(TbCategoryEntity entity) {
		return tbCategoryMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	public TbCategoryEntity getCategoryById(Long id) {
		return tbCategoryMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	public int deleteCategoryById(Long id) {
		return tbCategoryMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<TbCategoryEntity> getCategoryListByPage(int page) {
		return tbCategoryMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 查询数据总数
	 * 
	 * @return
	 */
	public int getCategoryCount() {
		return tbCategoryMapper.selectCount();
	}

	/**
	 * 模糊查询
	 * @param search
	 * @param page
	 * @return
	 */
	public List<TbCategoryEntity> getCategoryListBySearchAndPage(String search, int page) {
		return tbCategoryMapper.selectBySearchAndPage(search, (page - 1) * count, count);
	}

	/**
	 * 检索的数据总数
	 * @param search
	 * @return
	 */
	public int getCategoryCountBySearch(String search) {
		return tbCategoryMapper.selectCountBySearch(search);
	}

	/**
	 * 获取所有数据
	 * @return
	 */
	public List<TbCategoryEntity> getCategoryList() {
		return tbCategoryMapper.selectAll();
	}
}
