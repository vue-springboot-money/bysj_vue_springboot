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
	public TbUserEntity login(LoginDto login) {
		String md5Password = MD5Util.encode(login.getPassword());
		
		TbUserEntity selectResult = tbUserMapper.selectByUsername(login.getUserName());
		if (selectResult != null && md5Password.equals(selectResult.getPassword())) {
			return selectResult;
		} else {
			return null;
		}
		
	}

}
