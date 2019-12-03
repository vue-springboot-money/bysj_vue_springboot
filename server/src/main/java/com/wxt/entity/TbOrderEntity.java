package com.wpc.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("订单")
@AllArgsConstructor
public class TbOrderEntity {
	@ApiModelProperty(value = "自增主键")
	private Long id;
	
	@ApiModelProperty(value = "订单编号")
	private String no;
	
	@ApiModelProperty(value = "台号表主键")
	private Long did;
	
	@ApiModelProperty(value = "价格")
	private float price;
	
	@ApiModelProperty(value = "状态（0：未结账，1：已结账）")
	private byte state;
	
	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
