package com.wxt.dto;

import java.util.List;

import com.wxt.entity.TbDeskEntity;
import com.wxt.entity.TbOrderEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("订单DTO")
public class OrderDto {

	// 提交用
	@ApiModelProperty(value = "台号id")
	private Long did;

	@ApiModelProperty(value = "订单总额")
	private Float price;

	@ApiModelProperty(value = "菜单集合")
	private List<OrderItemDto> itemList;
	
	private TbOrderEntity order;
	
	private TbDeskEntity desk;

}