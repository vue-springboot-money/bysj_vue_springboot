package com.example.service;

import java.util.List;

import com.example.entity.TbUser;

public interface UserService {

	/**
	 * 创建用户
	 * @param tbUser
	 * @return
	 */
	public TbUser createUser(TbUser tbUser);
	
	public int removeUser(Long id);
	
	public List<TbUser> findAllUser();
	
	public TbUser findUserById(Long id);
	
	public List<TbUser> findUserByUsername(String username);
	
	public List<TbUser> findUserByType(byte type);
}
