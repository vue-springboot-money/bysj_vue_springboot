package com.ttcanteen.service;

import java.util.List;

import com.ttcanteen.entity.TbMenuEntity;

public interface MenuService {

	/**
	 * 创建菜单
	 * @param entity
	 * @return
	 */
	TbMenuEntity createMenu(TbMenuEntity entity);

	/**
	 * 根据id获取菜单信息
	 * @param id
	 * @return
	 */
	TbMenuEntity findMenuById(Long id);

	/**
	 * 分页查询
	 * @param pageNum
	 * @return
	 */
	List<TbMenuEntity> selectMenuListByPage(int pageNum);

	/**
	 * 更新菜单信息
	 */
	TbMenuEntity updateMenu(TbMenuEntity entity);

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
