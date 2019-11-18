package com.ttcanteen.dto;

import java.util.List;

import com.ttcanteen.entity.TbOrderEntity;
import com.ttcanteen.entity.TbUserEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("订单DTO")
public class OrderDto {

	// 提交用
	@ApiModelProperty(value = "用户id")
	private Long uid;

	@ApiModelProperty(value = "订单总额")
	private Float price;

	@ApiModelProperty(value = "菜单集合")
	private List<OrderItemDto> itemList;
	
	// 显示用
	private TbUserEntity user;
	
	private TbOrderEntity orderEntity;

}
