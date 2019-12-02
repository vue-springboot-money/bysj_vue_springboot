package com.wpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wpc.entity.TbMenuEntity;
import com.wpc.mapper.TbMenuMapper;

@Service
public class MenuService {

	@Autowired
	private TbMenuMapper tbMenuMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建菜单
	 * 
	 * @param entity
	 * @return
	 */
	public int createMenu(TbMenuEntity entity) {
		return tbMenuMapper.insert(entity);
	}

	/**
	 * 更新菜单信息
	 * 
	 * @param entity
	 * @return
	 */
	public int updateMenu(TbMenuEntity entity) {
		return tbMenuMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的菜单
	 * 
	 * @param id
	 * @return
	 */
	public TbMenuEntity getMenuById(Long id) {
		return tbMenuMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的菜单
	 * 
	 * @param id
	 * @return
	 */
	public int deleteMenuById(Long id) {
		return tbMenuMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<TbMenuEntity> getMenuListByPage(int page) {
		return tbMenuMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 查询菜单总数
	 * 
	 * @return
	 */
	public int getMenuCount() {
		return tbMenuMapper.selectCount();
	}

	/**
	 * 模糊查询
	 * @param search
	 * @param page
	 * @return
	 */
	public List<TbMenuEntity> getMenuListBySearchAndPage(String search, int page) {
		return tbMenuMapper.selectBySearchAndPage(search, (page - 1) * count, count);
	}

	/**
	 * 检索的菜单总数
	 * @param search
	 * @return
	 */
	public int getMenuCountBySearch(String search) {
		return tbMenuMapper.selectCountBySearch(search);
	}
}
