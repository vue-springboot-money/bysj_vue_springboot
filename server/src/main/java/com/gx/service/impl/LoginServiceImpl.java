package com.gx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gx.dto.LoginDto;
import com.gx.entity.TbUserEntity;
import com.gx.mapper.TbUserMapper;
import com.gx.service.LoginService;
import com.gx.utils.MD5Util;

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
		TbUserEntity selectResult = tbUserMapper.selectByUsername(login.getUserName());
		if (selectResult != null && md5Password.equals(selectResult.getPassword())) {
			// 返回登录对象
			return selectResult;
		} else {
			// 查询不到用户或者密码不匹配，返回空
			return null;
		}
	}

}
