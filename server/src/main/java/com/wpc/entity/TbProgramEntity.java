package com.wpc.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("节目单")
@AllArgsConstructor
public class TbProgramEntity {
	@ApiModelProperty(value = "自增主键")
	private Long id;
	
	@ApiModelProperty(value = "剧场外键")
	private Long tid;
	
	@ApiModelProperty(value = "节目单内容")
	private Long content;
	
	@ApiModelProperty(value = "演出日期")
	private Date date;
	
	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
