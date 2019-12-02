package com.wpc.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("菜单")
@AllArgsConstructor
public class TbMenuEntity {
	@ApiModelProperty(value = "id")
	private Long id;
	
	@ApiModelProperty(value = "菜单名")
	private String name;
	
	@ApiModelProperty(value = "图片")
	private String img;
	
	@ApiModelProperty(value = "价格")
	private float price;
	
	@ApiModelProperty(value = "状态（0：未上架，1：已上架）")
	private byte state;
	
	@ApiModelProperty(value = "分类外键")
	private Long cid;

	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
