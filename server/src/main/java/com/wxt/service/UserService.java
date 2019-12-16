package com.wxt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wxt.entity.TbUserEntity;
import com.wxt.mapper.TbUserMapper;
import com.wxt.utils.MD5Util;

@Service
public class UserService {

	@Autowired
	private TbUserMapper tbUserMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建
	 * 
	 * @param entity
	 * @return
	 */
	public int createUser(TbUserEntity entity) {
		entity.setPassword(MD5Util.encode(entity.getPassword() == null ? "123456" : entity.getPassword()));
		return tbUserMapper.insert(entity);
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 */
	public int updateUser(TbUserEntity entity) {
		return tbUserMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	public TbUserEntity getUserById(Long id) {
		return tbUserMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的数据
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
	 * 查询数据总数
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
	 * 检索的数据总数
	 * @param search
	 * @return
	 */
	public int getUserCountBySearch(String search) {
		return tbUserMapper.selectCountBySearch(search);
	}

	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	public TbUserEntity getUserByUsername(String username) {
		return tbUserMapper.selectByUsername(username);
	}

	public int changePwd(Long id, String pwd) {
		// 密码加密
		pwd = MD5Util.encode(pwd);
		// 进行更新操作（返回值为1则更新成功）
		int updateResult = tbUserMapper.updatePwdById(id, pwd);
		return updateResult;
	}
}
