package com.wpc.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("交接班表")
@AllArgsConstructor
public class TbShiftEntity {
	@ApiModelProperty(value = "自增主键")
	private Long id;
	
	@ApiModelProperty(value = "台号表主键")
	private Long uid;
	
	@ApiModelProperty(value = "接班时间")
	private Date start;
	
	@ApiModelProperty(value = "交班时间")
	private Date end;
	
	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
