package com.wpc.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("商品类别与商品关系")
@AllArgsConstructor
@NoArgsConstructor
public class TbCategoryGood {

	private Long id;
	
	private Long cid;
	
	private Long gid;
	
	private Date createtime;
}
