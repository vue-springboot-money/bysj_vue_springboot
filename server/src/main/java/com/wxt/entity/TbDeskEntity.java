package com.wpc.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("台号")
@AllArgsConstructor
public class TbDeskEntity {
	@ApiModelProperty(value = "id")
	private Long id;
	
	@ApiModelProperty(value = "台号名")
	private String name;
	
	@ApiModelProperty(value = "状态（0：空闲，1：在用）")
	private byte state;
	
	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
