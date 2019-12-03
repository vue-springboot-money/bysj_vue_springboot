package com.gx.service.impl;

import java.util.List;

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

	@Override
	public int update(TbLogisticsEntity entity) {
		return tbLogisticsMapper.update(entity);
	}

	@Override
	public List<TbLogisticsEntity> getLogisticsListByUidAndPage(Long uid, int page) {
		int count = 10;
		return tbLogisticsMapper.selectByUidAndPage(uid, (page - 1) * count, count);
	}


}
