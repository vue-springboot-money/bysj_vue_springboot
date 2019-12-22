package com.wpc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@ApiModel("订单")
@AllArgsConstructor
@NoArgsConstructor
public class TbOrderEntity {
	@ApiModelProperty(value = "自增主键")
	private Long id;

	@ApiModelProperty(value = "订单编号")
	private String no;
	
	@ApiModelProperty(value = "用户外键")
	private Long uid;
	
	@ApiModelProperty(value = "门票外键")
	private Long tid;
	
	@ApiModelProperty(value = "状态（0：未完成，1：已完成，2：已退款）")
	private byte state;
	
	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
