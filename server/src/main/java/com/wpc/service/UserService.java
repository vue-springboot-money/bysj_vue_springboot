package com.wpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wpc.entity.TbUserEntity;
import com.wpc.mapper.TbUserMapper;
import com.wpc.utils.MD5Util;

@Service
public class UserService {

	@Autowired
	private TbUserMapper tbUserMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建用户
	 * 
	 * @param entity
	 * @return
	 */
	public int createUser(TbUserEntity entity) {
		entity.setPassword(MD5Util.encode(entity.getPassword() == null ? "123456" : entity.getPassword()));
		entity.setBalance(entity.getBalance() == null ? 0 : entity.getBalance());
		return tbUserMapper.insert(entity);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param entity
	 * @return
	 */
	public int updateUser(TbUserEntity entity) {
		return tbUserMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的用户
	 * 
	 * @param id
	 * @return
	 */
	public TbUserEntity getUserById(Long id) {
		return tbUserMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	public TbUserEntity getUserByUsername(String username) {
		return tbUserMapper.selectByUsername(username);
	}

	/**
	 * 删除指定id的用户
	 * 
	 * @param id
	 * @return
	 */
	public int deleteUserById(Long id) {
		return tbUserMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<TbUserEntity> getUserListByPage(int page) {
		return tbUserMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 查询用户总数
	 * 
	 * @return
	 */
	public int getUserCount() {
		return tbUserMapper.selectCount();
	}

	/**
	 * 模糊查询
	 * @param search
	 * @param page
	 * @return
	 */
	public List<TbUserEntity> getUserListBySearchAndPage(String search, int page) {
		return tbUserMapper.selectBySearchAndPage(search, (page - 1) * count, count);
	}

	/**
	 * 检索的用户总数
	 * @param search
	 * @return
	 */
	public int getUserCountBySearch(String search) {
		return tbUserMapper.selectCountBySearch(search);
	}

	/**
	 * 充值
	 * @param id
	 * @param money
	 * @return
	 */
	public int recharge(Long id, Float money) {
		return tbUserMapper.addBalanceById(id, money);
	}

	/**
	 * 消费
	 * @param id
	 * @param money
	 * @return
	 */
	public int consume(Long id, Float money) {
		return tbUserMapper.reduceBalanceById(id, money);
	}
}
