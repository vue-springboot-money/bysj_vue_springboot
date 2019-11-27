package com.wpc.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("演员")
@AllArgsConstructor
public class TbActorEntity {
	@ApiModelProperty(value = "id")
	private Long id;
	
	@ApiModelProperty(value = "演员名")
	private String name;
	
	@ApiModelProperty(value = "图片")
	private String img;
	
	@ApiModelProperty(value = "演员信息")
	private String information;
	
	@ApiModelProperty(value = "代表作")
	private String masterpiece;
	
	@ApiModelProperty(value = "队伍外键")
	private Long tid;

	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
