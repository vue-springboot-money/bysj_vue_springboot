package com.wpc.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("队伍")
@AllArgsConstructor
public class TbTeamEntity {
	@ApiModelProperty(value = "id")
	private Long id;
	
	@ApiModelProperty(value = "队伍名")
	private String name;
	
	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
