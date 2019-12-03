package com.wxt.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("订单详情")
@AllArgsConstructor
public class TbOrderTempEntity {
	@ApiModelProperty(value = "自增主键")
	private Long id;
	
	@ApiModelProperty(value = "台号表主键")
	private Long did;
	
	@ApiModelProperty(value = "菜单表主键")
	private Long mid;
	
	@ApiModelProperty(value = "数量")
	private int amount;
	
	@ApiModelProperty(value = "状态（0：未签单，1：已签单）")
	private byte state;
	
	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
