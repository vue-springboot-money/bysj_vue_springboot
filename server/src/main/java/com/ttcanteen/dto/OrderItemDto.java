package com.ttcanteen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemDto {

	private Long id;

	private String name;

	private Float price;

	private int count;
}
