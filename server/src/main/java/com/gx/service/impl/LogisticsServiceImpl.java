package com.gx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gx.entity.TbLogisticsEntity;
import com.gx.mapper.TbLogisticsMapper;
import com.gx.service.LogisticsService;

@Service
public class LogisticsServiceImpl implements LogisticsService {

	@Autowired
	private TbLogisticsMapper tbLogisticsMapper;

	@Override
	public TbLogisticsEntity getLogisticsByOid(Long oid) {
		return tbLogisticsMapper.selectByOid(oid);
	}


}
