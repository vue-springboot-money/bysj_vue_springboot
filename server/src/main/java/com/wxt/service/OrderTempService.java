package com.wxt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wxt.entity.TbOrderTempEntity;
import com.wxt.mapper.TbOrderTempMapper;

@Service
public class OrderTempService {

	@Autowired
	private TbOrderTempMapper tbOrderTempMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建
	 * 
	 * @param entity
	 * @return
	 */
	public int createOrderTemp(TbOrderTempEntity entity) {
		return tbOrderTempMapper.insert(entity);
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 */
	public int updateOrderTemp(TbOrderTempEntity entity) {
		return tbOrderTempMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	public TbOrderTempEntity getOrderTempById(Long id) {
		return tbOrderTempMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	public int deleteOrderTempById(Long id) {
		return tbOrderTempMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<TbOrderTempEntity> getOrderTempListByPage(int page) {
		return tbOrderTempMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 查询数据总数
	 * 
	 * @return
	 */
	public int getOrderTempCount() {
		return tbOrderTempMapper.selectCount();
	}

}
