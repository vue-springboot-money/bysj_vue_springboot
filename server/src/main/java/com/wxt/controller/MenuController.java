package com.wxt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wxt.dto.MenuDto;
import com.wxt.entity.TbMenuEntity;
import com.wxt.pojo.Common;
import com.wxt.pojo.ResultPojo;
import com.wxt.service.CategoryService;
import com.wxt.service.MenuService;

@RestController()
@Transactional
public class MenuController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private CategoryService categoryService;

	/**
	 * 创建
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("menu")
	public ResultPojo createMenu(@RequestBody TbMenuEntity entity) {

		int result = menuService.createMenu(entity);

		// 注册失败
		if (result != 1) {
			return new ResultPojo(Common.ERR, entity);
		} else {
			// 注册成功
			return new ResultPojo(Common.OK, entity);
		}
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("menu")
	public ResultPojo modifyMenu(@RequestBody TbMenuEntity entity) {
		int updateResult = menuService.updateMenu(entity);

		// 更新成功
		if (updateResult != 0) {
			return new ResultPojo(Common.OK, entity);
		} else {
			// 更新失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 查询指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("menu/id/{id}")
	public ResultPojo getMenuById(@PathVariable Long id) {
		TbMenuEntity result = menuService.getMenuById(id);

		// 查询成功
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			// 查询失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 删除指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("menu/id/{id}")
	public ResultPojo deleteMenu(@PathVariable Long id) {
		int deleteResult = menuService.deleteMenuById(id);

		// 删除成功
		if (deleteResult == 1) {
			return new ResultPojo(Common.OK, deleteResult);
		} else {
			// 删除失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @return
	 */
	@GetMapping("menu/page/{page}")
	public ResultPojo getMenuListByPage(@PathVariable int page) {
		List<TbMenuEntity> menuList = menuService.getMenuListByPage(page);

		// 查询失败（没有数据）
		if (menuList == null || menuList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询成功
			List<MenuDto> dtoList = new ArrayList<>();

			for (TbMenuEntity entity : menuList) {
				dtoList.add(new MenuDto(entity.getId(), entity.getName(), entity.getImg(), entity.getPrice(),
						entity.getState(), entity.getCid(), entity.getCreatetime(),
						categoryService.getCategoryById(entity.getCid())));
			}
			return new ResultPojo(Common.OK, dtoList);
		}
	}

	/**
	 * 获取所有数据
	 * 
	 * @return
	 */
	@GetMapping("menu/count")
	public ResultPojo getMenuCount() {
		return new ResultPojo(Common.OK, menuService.getMenuCount());
	}

	/**
	 * 模糊查询
	 * 
	 * @param searchTxt
	 * @param pageNum
	 * @return
	 */
	@GetMapping("menu/search/{search}/page/{page}")
	public ResultPojo getMenuListBySearchAndPage(@PathVariable("search") String search,
			@PathVariable("page") int page) {
		return new ResultPojo(Common.OK, menuService.getMenuListBySearchAndPage(search, page));
	}

	/**
	 * 获取符合检索条件的所有数据数量
	 * 
	 * @param searchTxt
	 * @return
	 */
	@GetMapping("menu/search/{search}/count")
	public ResultPojo getMenuCountBySearch(@PathVariable String search) {
		return new ResultPojo(Common.OK, menuService.getMenuCountBySearch(search));
	}
	
	/**
	 * 获取所有数据
	 * 
	 * @param 
	 * @return
	 */
	@GetMapping("menuList")
	public ResultPojo getMenuList() {
		return new ResultPojo(Common.OK, menuService.getMenuList());
	}
}
