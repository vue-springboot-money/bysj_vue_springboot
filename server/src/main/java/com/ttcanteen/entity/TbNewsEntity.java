package com.ttcanteen.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("新闻")
@AllArgsConstructor
public class TbNewsEntity {
	@ApiModelProperty(value = "id")
	private Long id;
	
	@ApiModelProperty(value = "新闻标题", required=true)
	private String title;
	
	@ApiModelProperty(value = "新闻配图路径")
	private Long img;
	
	@ApiModelProperty(value = "作者署名", required=true)
	private Long author;
	
	@ApiModelProperty(value = "状态（0：未发布，1：已发布）")
	private byte state;

	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
