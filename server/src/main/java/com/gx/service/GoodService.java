package com.gx.service;

import java.util.List;

import com.gx.entity.TbGoodEntity;

public interface GoodService {

	/**
	 * 创建商品
	 * @param entity
	 * @return
	 */
	TbGoodEntity createGood(TbGoodEntity entity);

	/**
	 * 根据id获取商品信息
	 * @param id
	 * @return
	 */
	TbGoodEntity findGoodById(Long id);

	/**
	 * 分页查询
	 * @param pageNum
	 * @return
	 */
	List<TbGoodEntity> selectGoodListByPage(int pageNum);

	/**
	 * 更新商品信息
	 */
	int updateGood(TbGoodEntity entity);

	/**
	 * 删除商品
	 * @param id
	 * @return
	 */
	int deleteGood(Long id);

	/**
	 * 获取数据总数
	 * @return
	 */
	int selectSum();
	
	/**
	 * 根据分类分页查询
	 * @param cid
	 * @param page
	 * @return
	 */
	List<TbGoodEntity> selectGoodListByCidAndPage(Long cid, int page);

}
