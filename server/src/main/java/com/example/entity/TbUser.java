package com.example.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("用户")
@AllArgsConstructor
public class TbUser {
	@ApiModelProperty(value = "id")
	private Long id;
	
	@ApiModelProperty(value = "用户名")
	private String username;
	
	@ApiModelProperty(value = "密码")
	private String password;
	
	@ApiModelProperty(value = "用户类型（0: 管理员, 1: 学生, 2: 教师）")
	private byte type;

}
