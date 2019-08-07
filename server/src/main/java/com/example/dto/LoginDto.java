package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {
	// 用户名
	private String username;
	
	// 密码
	private String password;
}
