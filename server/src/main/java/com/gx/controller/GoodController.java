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

import com.gx.entity.TbGoodEntity;
import com.gx.pojo.Common;
import com.gx.pojo.ResultPojo;
import com.gx.service.GoodService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController()
@Api(description = "商品相关接口")
@Transactional
public class GoodController {

	@Autowired
	private GoodService goodService;

	/**
	 * 新建商品
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("good")
	@ApiOperation("创建商品")
	public ResultPojo creategood(@RequestBody TbGoodEntity entity) {

		TbGoodEntity result = goodService.createGood(entity);

		// 创建失败
		if (result == null) {
			return new ResultPojo(Common.ERR, result);
		} else {
			// 创建成功
			return new ResultPojo(Common.OK, result);
		}
	}

	/**
	 * 获取商品信息
	 * 
	 * @param entity
	 * @return
	 */
	@GetMapping("good/{id}")
	@ApiOperation("获取指定id的商品信息")
	public ResultPojo changePwd(@PathVariable Long id) {
		TbGoodEntity result = goodService.findGoodById(id);

		// 查询成功
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			// 查询失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	@GetMapping("goodTotal")
	@ApiOperation("获取数据条数（分页插件用）")
	public ResultPojo getgoodSum() {
		int goodSum = goodService.selectSum();

		return new ResultPojo(Common.OK, goodSum);
	}

	@GetMapping("goods/{pageNum}")
	@ApiOperation("分页查询商品")
	public ResultPojo getgoodListByPage(@PathVariable int pageNum) {
		List<TbGoodEntity> goodList = goodService.selectGoodListByPage(pageNum);

		// 查询成功
		if (goodList == null || goodList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, goodList);
		}
	}

	/**
	 * 修改商品信息
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("good")
	@ApiOperation("修改商品信息")
	public ResultPojo modifygood(@RequestBody TbGoodEntity entity) {
		TbGoodEntity updateResult = goodService.updateGood(entity);

		// 更新成功
		if (updateResult != null) {
			return new ResultPojo(Common.OK, updateResult);
		} else {
			// 更新失败
			return new ResultPojo(Common.ERR, null);
		}
	}


	@DeleteMapping("good/{id}")
	@ApiOperation("删除商品")
	public ResultPojo deletegood(@PathVariable Long id) {
		int deleteResult = goodService.deleteGood(id);

		// 删除成功
		if (deleteResult == 1) {
			return new ResultPojo(Common.OK, id);
		} else {
			// 删除失败
			return new ResultPojo(Common.ERR, null);
		}
	}

}
