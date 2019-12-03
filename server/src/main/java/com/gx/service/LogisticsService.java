package com.gx.service;

import java.util.List;

import com.gx.entity.TbLogisticsEntity;

public interface LogisticsService {

	/**
	 * 根据订单id获取物流信息
	 * @param id
	 * @return
	 */
	TbLogisticsEntity getLogisticsByOid(Long oid);

	/**
	 * 更新物流信息
	 * @param entity
	 * @return
	 */
	int update(TbLogisticsEntity entity);

	/**
	 * 根据uid获取所有物流订单
	 * @param uid
	 * @return
	 */
	List<TbLogisticsEntity> getLogisticsListByUidAndPage(Long uid, int page);

}
