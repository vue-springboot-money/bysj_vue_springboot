package com.ttcanteen.controller;

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

import com.ttcanteen.entity.TbMenuEntity;
import com.ttcanteen.pojo.Common;
import com.ttcanteen.pojo.ResultPojo;
import com.ttcanteen.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController()
@Api(description = "菜单相关接口")
@Transactional
public class MenuController {

	@Autowired
	private MenuService menuService;

	/**
	 * 新建菜单
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("menu")
	@ApiOperation("创建菜品")
	public ResultPojo createmenu(@RequestBody TbMenuEntity entity) {

		TbMenuEntity result = menuService.createMenu(entity);

		// 创建失败
		if (result == null) {
			return new ResultPojo(Common.ERR, result);
		} else {
			// 创建成功
			return new ResultPojo(Common.OK, result);
		}
	}

	/**
	 * 获取菜单信息
	 * 
	 * @param entity
	 * @return
	 */
	@GetMapping("menu/{id}")
	@ApiOperation("获取指定id的菜单信息")
	public ResultPojo changePwd(@PathVariable Long id) {
		TbMenuEntity result = menuService.findMenuById(id);

		// 查询成功
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			// 查询失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	@GetMapping("menuTotal")
	@ApiOperation("获取数据条数（分页插件用）")
	public ResultPojo getMenuSum() {
		int menuSum = menuService.selectSum();

		return new ResultPojo(Common.OK, menuSum);
	}

	@GetMapping("menus/{pageNum}")
	@ApiOperation("分页查询菜单")
	public ResultPojo getmenuListByPage(@PathVariable int pageNum) {
		List<TbMenuEntity> menuList = menuService.selectMenuListByPage(pageNum);

		// 查询成功
		if (menuList == null || menuList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, menuList);
		}
	}

	/**
	 * 修改菜单信息
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("menu")
	@ApiOperation("修改菜单信息")
	public ResultPojo modifymenu(@RequestBody TbMenuEntity entity) {
		TbMenuEntity updateResult = menuService.updateMenu(entity);

		// 更新成功
		if (updateResult != null) {
			return new ResultPojo(Common.OK, updateResult);
		} else {
			// 更新失败
			return new ResultPojo(Common.ERR, null);
		}
	}


	@DeleteMapping("menu/{id}")
	@ApiOperation("删除菜单")
	public ResultPojo deletemenu(@PathVariable Long id) {
		int deleteResult = menuService.deleteMenu(id);

		// 删除成功
		if (deleteResult == 1) {
			return new ResultPojo(Common.OK, id);
		} else {
			// 删除失败
			return new ResultPojo(Common.ERR, null);
		}
	}

}
