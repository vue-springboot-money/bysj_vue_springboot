package com.wpc.service;

import java.util.List;

import com.wpc.entity.TbOrderItemEntity;

public interface OrderItemService {

	/**
	 * 根据订单编号获取所有订单详情
	 * @param no
	 * @return
	 */
	List<TbOrderItemEntity> selectOrderItemByNo(String no);

}
