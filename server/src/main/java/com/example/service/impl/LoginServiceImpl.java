package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.LoginDto;
import com.example.entity.TbUser;
import com.example.mapper.TbUserMapper;
import com.example.service.LoginService;
import com.example.utils.MD5Util;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private TbUserMapper tbUserMapper;

	@Override
	public TbUser login(LoginDto login) {
		
		String md5Password = MD5Util.encode(login.getPassword());
		
		login.setPassword(md5Password);
		
		TbUser userFormDb = tbUserMapper.selectByUsername(login.getUsername()).get(0);
		
		if (userFormDb.getPassword().equals(md5Password)) {
			return userFormDb;
		} else {
			return null;
		}
	}

	@Override
	public TbUser signOut() {
		return null;
	}

}
