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

	public ResultPojo() {
		super();
	}

	public ResultPojo(int code, String msg, Object object) {
		super();
		this.code = code;
		this.msg = msg;
		this.object = object;
	}
}
