package com.wpc.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("专场")
@AllArgsConstructor
public class TbSessionEntity {
	@ApiModelProperty(value = "自增主键")
	private Long id;
	
	@ApiModelProperty(value = "主演1")
	private Long starring1;
	
	@ApiModelProperty(value = "主演2")
	private Long starring2;
	
	@ApiModelProperty(value = "助演1")
	private Long assistant1;
	
	@ApiModelProperty(value = "助演2")
	private Long assistant2;
	
	@ApiModelProperty(value = "助演3")
	private Long assistant3;
	
	@ApiModelProperty(value = "助演4")
	private Long assistant4;
	
	@ApiModelProperty(value = "剧场外键")
	private Long tid;
	
	@ApiModelProperty(value = "演出日期")
	private Date date;
	
	@ApiModelProperty(value = "图片")
	private String img;
	
	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
