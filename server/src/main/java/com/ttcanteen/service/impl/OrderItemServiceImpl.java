package com.ttcanteen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttcanteen.entity.TbOrderItemEntity;
import com.ttcanteen.mapper.TbOrderItemMapper;
import com.ttcanteen.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;

	@Override
	public List<TbOrderItemEntity> selectOrderItemByNo(String no) {
		return tbOrderItemMapper.selectByNo(no);
	}

}
