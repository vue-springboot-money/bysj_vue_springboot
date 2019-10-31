package com.ttcanteen.dto;

import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel("订单DTO")
public class OrderDto {
	
	@ApiModelProperty(value = "用户id")
	private Long uid;
	
	@ApiModelProperty(value = "菜单集合")
	private Map<Long, Integer> menuMap;
}
