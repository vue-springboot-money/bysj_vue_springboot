package com.wxt.controller;

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

import com.wxt.entity.TbCategoryEntity;
import com.wxt.pojo.Common;
import com.wxt.pojo.ResultPojo;
import com.wxt.service.CategoryService;

@RestController()
@Transactional
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * 创建
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("category")
	public ResultPojo createCategory(@RequestBody TbCategoryEntity entity) {

		int result = categoryService.createCategory(entity);

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
	@PatchMapping("category")
	public ResultPojo modifyCategory(@RequestBody TbCategoryEntity entity) {
		int updateResult = categoryService.updateCategory(entity);

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
	@GetMapping("category/id/{id}")
	public ResultPojo getCategoryById(@PathVariable Long id) {
		TbCategoryEntity result = categoryService.getCategoryById(id);

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
	@DeleteMapping("category/id/{id}")
	public ResultPojo deleteCategory(@PathVariable Long id) {
		int deleteResult = categoryService.deleteCategoryById(id);

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
	@GetMapping("category/page/{page}")
	public ResultPojo getCategoryListByPage(@PathVariable int page) {
		List<TbCategoryEntity> categoryList = categoryService.getCategoryListByPage(page);

		// 查询成功
		if (categoryList == null || categoryList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, categoryList);
		}
	}

	/**
	 * 获取所有数据
	 * 
	 * @return
	 */
	@GetMapping("category/count")
	public ResultPojo getCategoryCount() {
		return new ResultPojo(Common.OK, categoryService.getCategoryCount());
	}

	/**
	 * 模糊查询
	 * 
	 * @param searchTxt
	 * @param pageNum
	 * @return
	 */
	@GetMapping("category/search/{search}/page/{page}")
	public ResultPojo getCategoryListBySearchAndPage(@PathVariable("search") String search,
			@PathVariable("page") int page) {
		return new ResultPojo(Common.OK, categoryService.getCategoryListBySearchAndPage(search, page));
	}

	/**
	 * 获取符合检索条件的所有数据数量
	 * 
	 * @param searchTxt
	 * @return
	 */
	@GetMapping("category/search/{search}/count")
	public ResultPojo getCategoryCountBySearch(@PathVariable String search) {
		return new ResultPojo(Common.OK, categoryService.getCategoryCountBySearch(search));
	}

	/**
	 * 获取所有数据
	 * 
	 * @param
	 * @return
	 */
	@GetMapping("categoryList")
	public ResultPojo getCategoryList() {
		return new ResultPojo(Common.OK, categoryService.getCategoryList());
	}

}
