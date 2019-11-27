package com.wpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.entity.TbOrderItemEntity;
import com.wpc.mapper.TbOrderItemMapper;
import com.wpc.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;

	@Override
	public List<TbOrderItemEntity> selectOrderItemByNo(String no) {
		return tbOrderItemMapper.selectByNo(no);
	}

}
