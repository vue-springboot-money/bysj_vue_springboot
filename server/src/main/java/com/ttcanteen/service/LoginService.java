package com.ttcanteen.service;

import com.ttcanteen.dto.LoginDto;
import com.ttcanteen.entity.TbUserEntity;

public interface LoginService {
	
	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	TbUserEntity login(LoginDto user);

}
