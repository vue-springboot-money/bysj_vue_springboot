package com.wxt.dto;

import java.util.Date;

import com.wxt.entity.TbCategoryEntity;
import com.wxt.entity.TbDeskEntity;
import com.wxt.entity.TbMenuEntity;
import com.wxt.entity.TbOrderTempEntity;

import lombok.Data;

@Data
public class OrderTempDto extends TbOrderTempEntity{
	private TbDeskEntity desk;
	private TbMenuEntity menu;
	public OrderTempDto(Long id, Long did, Long mid, int amount, byte state, Date createtime,
			TbDeskEntity desk, TbMenuEntity menu) {
		super(id, did, mid, amount, state, createtime);
		this.desk = desk;
		this.menu = menu;
	}
}
