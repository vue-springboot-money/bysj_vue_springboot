package com.gx.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("新闻")
@AllArgsConstructor
public class TbNewsEntity {
	@ApiModelProperty(value = "id")
	private Long id;
	
	@ApiModelProperty(value = "新闻标题")
	private String title;
	
	@ApiModelProperty(value = "新闻配图路径")
	private String img;
	
	@ApiModelProperty(value = "新闻内容")
	private String content;
	
	@ApiModelProperty(value = "作者署名")
	private String author;
	
	@ApiModelProperty(value = "状态（0：未发布，1：已发布）")
	private byte state;

	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
