package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gx.entity.TbOrderItemEntity;
import com.gx.mapper.TbOrderItemMapper;
import com.gx.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;

	@Override
	public List<TbOrderItemEntity> selectOrderItemByNo(String no) {
		return tbOrderItemMapper.selectByNo(no);
	}

}
