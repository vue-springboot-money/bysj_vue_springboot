package com.example.service;

import com.example.dto.LoginDto;
import com.example.entity.TbUser;

public interface LoginService {
	
	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	TbUser login(LoginDto user);
	
	TbUser signOut();

}
