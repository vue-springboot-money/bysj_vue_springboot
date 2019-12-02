package com.gx.dto;

import com.gx.entity.TbLogisticsEntity;
import com.gx.entity.TbUserEntity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("订单DTO")
public class LogisticsDto {

	private TbLogisticsEntity logisticsEntity;
	private TbUserEntity userEntity;
}
