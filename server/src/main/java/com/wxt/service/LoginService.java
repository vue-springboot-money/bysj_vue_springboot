package com.wxt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxt.dto.LoginDto;
import com.wxt.entity.TbUserEntity;
import com.wxt.mapper.TbUserMapper;
import com.wxt.utils.MD5Util;

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
