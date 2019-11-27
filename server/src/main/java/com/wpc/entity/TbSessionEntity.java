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
	
	@ApiModelProperty(value = "主演")
	private Long starring;
	
	@ApiModelProperty(value = "助演")
	private Long assistant;
	
	@ApiModelProperty(value = "节目单内容")
	private Long tid;
	
	@ApiModelProperty(value = "演出日期")
	private Date date;
	
	@ApiModelProperty(value = "图片")
	private String img;
	
	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
