package com.gx.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("商品类别")
@AllArgsConstructor
@NoArgsConstructor
public class TbCategory {

	private Long id;
	
	private String name;
	
	private byte state;
	
	private Date createtime;
}
