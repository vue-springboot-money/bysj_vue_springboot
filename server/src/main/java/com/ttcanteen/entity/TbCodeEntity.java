package com.ttcanteen.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("取餐码")
@AllArgsConstructor
public class TbCodeEntity {
	@ApiModelProperty(value = "id")
	private Long id;
	
	@ApiModelProperty(value = "订单编号")
	private String no;
	
	@ApiModelProperty(value = "取餐码(六位纯数字)")
	private String code;
	
	@ApiModelProperty(value = "状态（0：进行中，1：已完成）")
	private byte state;

	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
