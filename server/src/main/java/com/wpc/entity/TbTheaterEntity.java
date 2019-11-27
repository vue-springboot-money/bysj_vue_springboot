package com.wpc.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("剧场")
@AllArgsConstructor
public class TbTheaterEntity {
	@ApiModelProperty(value = "id")
	private Long id;
	
	@ApiModelProperty(value = "剧场名")
	private String name;
	
	@ApiModelProperty(value = "VCR")
	private String video;
	
	@ApiModelProperty(value = "剧场信息")
	private String information;
	
	@ApiModelProperty(value = "地址")
	private String address;
	
	@ApiModelProperty(value = "电话")
	private String tel;

	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
