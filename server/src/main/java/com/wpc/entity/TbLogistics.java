package com.wpc.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("物流信息")
@AllArgsConstructor
@NoArgsConstructor
public class TbLogistics {

	private Long id;
	
	private Long oid;
	
	private Long uid;
	
	private byte state;
	
	private String content;
	
	private Date createtime;
}
