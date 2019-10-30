package com.ttcanteen.entity;

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
	
	@ApiModelProperty(value = "菜品图片路径")
	private String img;
	
	@ApiModelProperty(value = "菜品价格")
	private Float price;
	
	@ApiModelProperty(value = "状态（0：未上架，1：已上架）")
	private byte state;

	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
