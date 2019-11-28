package com.wpc.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("门票")
@AllArgsConstructor
public class TbTicketEntity {
	@ApiModelProperty(value = "自增主键")
	private Long id;
	
	@ApiModelProperty(value = "节目外键")
	private Long pid;
	
	@ApiModelProperty(value = "专场外键")
	private Long sid;
	
	@ApiModelProperty(value = "价格")
	private float price;
	
	@ApiModelProperty(value = "数量")
	private Integer num;
	
	@ApiModelProperty(value = "乐观锁")
	private Integer version;
	
	@ApiModelProperty(value = "状态（0：未上架，1：已上架）")
	private byte state;
	
	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
