package com.wpc.service;

import com.wpc.dto.LoginDto;
import com.wpc.entity.TbUserEntity;

public interface LoginService {
	
	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	TbUserEntity login(LoginDto user);

}
