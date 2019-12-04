package com.wxt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel("登录DTO")
public class LoginDto {
	// 用户名
	@ApiModelProperty(value = "用户名")
	private String userName;
	
	// 密码
	@ApiModelProperty(value = "密码")
	private String password;
}
