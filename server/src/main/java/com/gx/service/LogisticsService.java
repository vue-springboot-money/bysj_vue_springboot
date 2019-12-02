package com.gx.service;

import com.gx.entity.TbLogisticsEntity;

public interface LogisticsService {

	/**
	 * 根据订单id获取物流信息
	 * @param id
	 * @return
	 */
	TbLogisticsEntity getLogisticsByOid(Long oid);

}
