package com.wpc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultPojo {

	// 返回的信息
	private String msg;

	// 返回的对象
	private Object object;
}
