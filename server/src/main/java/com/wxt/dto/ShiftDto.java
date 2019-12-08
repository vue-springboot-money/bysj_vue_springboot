package com.wxt.dto;

import java.util.Date;

import com.wxt.entity.TbShiftEntity;
import com.wxt.entity.TbUserEntity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("订单DTO")
public class ShiftDto extends TbShiftEntity{

	private TbUserEntity user;

	public ShiftDto(Long id, Long uid, Date start, Date end, Date createtime, TbUserEntity user) {
		super(id, uid, start, end, createtime);
		this.user = user;
	}
}