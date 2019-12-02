package com.gx.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("订单")
@AllArgsConstructor
@NoArgsConstructor
public class TbOrderEntity {
	@ApiModelProperty(value = "id")
	private Long id;
	
	@ApiModelProperty(value = "订单编号")
	private String no;
	
	@ApiModelProperty(value = "用户表主键")
	private Long uid;
	
	@ApiModelProperty(value = "订单总价")
	private Float price;
	
	@ApiModelProperty(value = "评价（订单状态为1后可进行评价）")
	private String comment;
	
	@ApiModelProperty(value = "状态（0：进行中，1：已完成）")
	private byte state;

	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
