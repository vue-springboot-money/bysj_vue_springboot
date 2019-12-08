package com.wxt.dto;

import java.util.Date;

import com.wxt.entity.TbCategoryEntity;
import com.wxt.entity.TbMenuEntity;

import lombok.Data;

@Data
public class MenuDto extends TbMenuEntity{
	private TbCategoryEntity category;

	public MenuDto(Long id, String name, String img, float price, byte state, Long cid, Date createtime,
			TbCategoryEntity category) {
		super(id, name, img, price, state, cid, createtime);
		this.category = category;
	}
}
