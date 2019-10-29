package com.ttcanteen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttcanteen.dto.LoginDto;
import com.ttcanteen.entity.TbUserEntity;
import com.ttcanteen.mapper.TbUserMapper;
import com.ttcanteen.service.LoginService;
import com.ttcanteen.utils.MD5Util;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private TbUserMapper tbUserMapper;

	@Override
	public TbUserEntity login(LoginDto login) {
		
		String md5Password = MD5Util.encode(login.getPassword());
		
		login.setPassword(md5Password);
		
		TbUserEntity userFormDb = tbUserMapper.selectByUsername(login.getUsername()).get(0);
		
		if (userFormDb.getPassword().equals(md5Password)) {
			return userFormDb;
		} else {
			return null;
		}
	}

}
