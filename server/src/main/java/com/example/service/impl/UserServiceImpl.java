package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.TbUser;
import com.example.mapper.TbUserMapper;
import com.example.service.UserService;
import com.example.utils.MD5Util;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private TbUserMapper tbUserMapper;

	@Override
	public TbUser createUser(TbUser tbUser) {
		
		// 加密密码
		tbUser.setPassword(MD5Util.encode(tbUser.getPassword()));
		
		int result = tbUserMapper.insert(tbUser);
		if (result == 1) {
			return tbUserMapper.selectByUsername(tbUser.getUsername()).get(0);
		} else {
			return null;
		}
	}

	@Override
	public int removeUser(Long id) {
		return tbUserMapper.deleteById(id);
	}

	@Override
	public List<TbUser> findAllUser() {
		return tbUserMapper.selectAll();
	}

	@Override
	public TbUser findUserById(Long id) {
		return tbUserMapper.selectById(id);
	}

	@Override
	public List<TbUser> findUserByUsername(String username) {
		return tbUserMapper.selectByUsername(username);
	}

	@Override
	public List<TbUser> findUserByType(byte type) {
		return tbUserMapper.selectByType(type);
	}

}
