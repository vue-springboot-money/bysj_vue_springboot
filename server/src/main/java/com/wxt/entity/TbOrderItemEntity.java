package com.wxt.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("订单详情")
@AllArgsConstructor
public class TbOrderItemEntity {
	@ApiModelProperty(value = "自增主键")
	private Long id;
	
	@ApiModelProperty(value = "订单编号")
	private String no;
	
	@ApiModelProperty(value = "菜单表主键")
	private Long mid;
	
	@ApiModelProperty(value = "数量")
	private int amount;
	
	@ApiModelProperty(value = "总价")
	private float price;
	
	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
