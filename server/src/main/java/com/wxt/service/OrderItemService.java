package com.wxt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wxt.entity.TbOrderItemEntity;
import com.wxt.mapper.TbOrderItemMapper;

@Service
public class OrderItemService {

	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建
	 * 
	 * @param entity
	 * @return
	 */
	public int createOrderItem(TbOrderItemEntity entity) {
		return tbOrderItemMapper.insert(entity);
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 */
	public int updateOrderItem(TbOrderItemEntity entity) {
		return tbOrderItemMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	public TbOrderItemEntity getOrderItemById(Long id) {
		return tbOrderItemMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	public int deleteOrderItemById(Long id) {
		return tbOrderItemMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<TbOrderItemEntity> getOrderItemListByPage(int page) {
		return tbOrderItemMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 查询数据总数
	 * 
	 * @return
	 */
	public int getOrderItemCount() {
		return tbOrderItemMapper.selectCount();
	}

}
