package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TbUser {
	
	private Long id;
	
	private String username;
	
	private String password;
	
	private byte type;

}
