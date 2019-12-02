package com.gx.controller;

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

import com.gx.entity.TbCategoryEntity;
import com.gx.pojo.Common;
import com.gx.pojo.ResultPojo;
import com.gx.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController()
@Api(description = "类别相关接口")
@Transactional
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * 新建类别
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("category")
	@ApiOperation("创建类别")
	public ResultPojo createcategory(@RequestBody TbCategoryEntity entity) {

		TbCategoryEntity result = categoryService.createCategory(entity);

		// 创建失败
		if (result == null) {
			return new ResultPojo(Common.ERR, result);
		} else {
			// 创建成功
			return new ResultPojo(Common.OK, result);
		}
	}

	/**
	 * 获取类别信息
	 * 
	 * @param entity
	 * @return
	 */
	@GetMapping("category/{id}")
	@ApiOperation("获取指定id的类别信息")
	public ResultPojo changePwd(@PathVariable Long id) {
		TbCategoryEntity result = categoryService.findCategoryById(id);

		// 查询成功
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			// 查询失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	@GetMapping("categoryTotal")
	@ApiOperation("获取数据条数（分页插件用）")
	public ResultPojo getcategorySum() {
		int categorySum = categoryService.selectSum();

		return new ResultPojo(Common.OK, categorySum);
	}

	@GetMapping("categorys/{pageNum}")
	@ApiOperation("分页查询类别")
	public ResultPojo getcategoryListByPage(@PathVariable int pageNum) {
		List<TbCategoryEntity> categoryList = categoryService.selectCategoryListByPage(pageNum);

		// 查询成功
		if (categoryList == null || categoryList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, categoryList);
		}
	}

	/**
	 * 修改类别信息
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("category")
	@ApiOperation("修改类别信息")
	public ResultPojo modifycategory(@RequestBody TbCategoryEntity entity) {
		TbCategoryEntity updateResult = categoryService.updateCategory(entity);

		// 更新成功
		if (updateResult != null) {
			return new ResultPojo(Common.OK, updateResult);
		} else {
			// 更新失败
			return new ResultPojo(Common.ERR, null);
		}
	}


	@DeleteMapping("category/{id}")
	@ApiOperation("删除类别")
	public ResultPojo deletecategory(@PathVariable Long id) {
		int deleteResult = categoryService.deleteCategory(id);

		// 删除成功
		if (deleteResult == 1) {
			return new ResultPojo(Common.OK, id);
		} else {
			// 删除失败
			return new ResultPojo(Common.ERR, null);
		}
	}
	
	@GetMapping("category/search/{searchTxt}/pageNum/{pageNum}")
	@ApiOperation("模糊查询")
	public ResultPojo searchCategory(@PathVariable("searchTxt") String searchTxt, @PathVariable("pageNum") int pageNum) {
		return new ResultPojo(Common.OK, categoryService.searchCategoryByPage(searchTxt, pageNum));
	}
	
	@GetMapping("searchCategoryTotal/{searchTxt}")
	@ApiOperation("模糊查询用户总数")
	public ResultPojo searchCategoryTotal(@PathVariable String searchTxt) {
		return new ResultPojo(Common.OK, categoryService.selectSearchCategoryTotal(searchTxt));
	}
	
	@GetMapping("categoryList")
	@ApiOperation("模糊查询用户总数")
	public ResultPojo categoryList() {
		return new ResultPojo(Common.OK, categoryService.selectAll());
	}
}
