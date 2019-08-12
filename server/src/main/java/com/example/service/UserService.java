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
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int removeUser(Long id);
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<TbUser> findAllUser();
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public TbUser findUserById(Long id);
	
	/**
	 * 根据username查询用户
	 * @param username
	 * @return
	 */
	public List<TbUser> findUserByUsername(String username);
	
	/**
	 * 根据type查询用户
	 * @param type
	 * @return
	 */
	public List<TbUser> findUserByType(byte type);
}
