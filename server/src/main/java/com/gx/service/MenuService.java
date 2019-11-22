package com.gx.service;

import java.util.List;

import com.gx.entity.TbGoodEntity;

public interface MenuService {

	/**
	 * 创建菜单
	 * @param entity
	 * @return
	 */
	TbGoodEntity createMenu(TbGoodEntity entity);

	/**
	 * 根据id获取菜单信息
	 * @param id
	 * @return
	 */
	TbGoodEntity findMenuById(Long id);

	/**
	 * 分页查询
	 * @param pageNum
	 * @return
	 */
	List<TbGoodEntity> selectMenuListByPage(int pageNum);

	/**
	 * 更新菜单信息
	 */
	TbGoodEntity updateMenu(TbGoodEntity entity);

	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	int deleteMenu(Long id);

	/**
	 * 获取数据总数
	 * @return
	 */
	int selectSum();

}
