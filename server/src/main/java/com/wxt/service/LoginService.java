package com.wpc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.dto.LoginDto;
import com.wpc.entity.TbUserEntity;
import com.wpc.mapper.TbUserMapper;
import com.wpc.utils.MD5Util;

@Service
public class LoginService {
	
	@Autowired
	private TbUserMapper tbUserMapper;
	
	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	public TbUserEntity login(LoginDto user) {
		
		TbUserEntity selectResult = tbUserMapper.selectByUsername(user.getUsername()).get(0);
		if (MD5Util.encode(user.getPassword()).equals(selectResult.getPassword())) {
			return selectResult;
		} else {
			return null;
		}
		
	}

}
