package com.wpc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.dto.LoginDto;
import com.wpc.entity.TbUserEntity;
import com.wpc.mapper.TbUserMapper;
import com.wpc.service.LoginService;
import com.wpc.utils.MD5Util;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private TbUserMapper tbUserMapper;

	@Override
	public TbUserEntity login(LoginDto login) {
		// 使用MD5进行密码加密
		String md5Password = MD5Util.encode(login.getPassword());
		
		login.setPassword(md5Password);
		
		// 根据用户名查询数据库中的用户
		TbUserEntity userFormDb = tbUserMapper.selectByUsername(login.getUsername()).get(0);
		
		// 查询到用户并且密码匹配
		if (userFormDb != null && userFormDb.getPassword().equals(md5Password)) {
			// 返回登录对象
			return userFormDb;
		} else {
			// 查询不到用户或者密码不匹配，返回空
			return null;
		}
	}

}
