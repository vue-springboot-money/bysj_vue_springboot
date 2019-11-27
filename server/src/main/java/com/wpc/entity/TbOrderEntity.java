package com.wpc.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("订单")
@AllArgsConstructor
public class TbOrderEntity {
	@ApiModelProperty(value = "自增主键")
	private Long id;
	
	@ApiModelProperty(value = "用户外键")
	private Long uid;
	
	@ApiModelProperty(value = "门票外键")
	private Long tid;
	
	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
