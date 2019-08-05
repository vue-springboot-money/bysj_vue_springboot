package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultPojo {
	// 状态码
	private int code;
	
	// 携带信息
	private String msg;
	
	// 返回的对象
	private Object object;

}
