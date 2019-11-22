package com.gx.service;

import com.gx.dto.LoginDto;
import com.gx.entity.TbUserEntity;

public interface LoginService {
	
	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	TbUserEntity login(LoginDto user);

}
