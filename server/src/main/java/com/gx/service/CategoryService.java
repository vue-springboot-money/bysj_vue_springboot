package com.gx.service;

import java.util.List;

import com.gx.entity.TbCategoryEntity;

public interface CategoryService {

	/**
	 * 创建类别
	 * @param entity
	 * @return
	 */
	TbCategoryEntity createCategory(TbCategoryEntity entity);

	/**
	 * 根据id获取类别信息
	 * @param id
	 * @return
	 */
	TbCategoryEntity findCategoryById(Long id);

	/**
	 * 分页查询
	 * @param pageNum
	 * @return
	 */
	List<TbCategoryEntity> selectCategoryListByPage(int pageNum);

	/**
	 * 更新类别信息
	 */
	TbCategoryEntity updateCategory(TbCategoryEntity entity);

	/**
	 * 删除类别
	 * @param id
	 * @return
	 */
	int deleteCategory(Long id);

	/**
	 * 获取数据总数
	 * @return
	 */
	int selectSum();
	
	/**
	 * 模糊查询
	 * @param searchTxt
	 * @param pageNum
	 * @return
	 */
	public List<TbCategoryEntity> searchCategoryByPage(String searchTxt, int pageNum);

	/**
	 * 检索的用户总数
	 * @return
	 */
	public int selectSearchCategoryTotal(String searchTxt);

	/**
	 * 获取所有类别
	 * @return
	 */
	List<TbCategoryEntity> selectAll();

}
