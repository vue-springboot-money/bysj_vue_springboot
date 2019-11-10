package com.ttcanteen.service;

import java.util.List;

import com.ttcanteen.entity.TbUserEntity;

public interface UserService {

	/**
	 * 创建用户
	 * @param entity
	 * @return
	 */
	public TbUserEntity createUser(TbUserEntity entity);

	/**
	 * 修改密码
	 * @param entity
	 * @return
	 */
	public int changePwd(TbUserEntity entity);

	/**
	 * 根据id获取用户信息
	 * @param id
	 * @return
	 */
	public TbUserEntity findUserById(Long id);

	/**
	 * 更新用户信息
	 * @param entity
	 * @return
	 */
	public TbUserEntity updateUser(TbUserEntity entity);

	/**
	 * 充值
	 * @param entity
	 * @return
	 */
	public TbUserEntity recharge(TbUserEntity entity);

	/**
	 * 消费
	 * @param entity
	 * @return
	 */
	public TbUserEntity consume(TbUserEntity entity);

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int deleteUser(Long id);

	/**
	 * 分页查询用户
	 * @return
	 */
	public List<TbUserEntity> selectUserListByPage(int pageNum);

	/**
	 * 查询用户总数
	 * @return
	 */
	public int selectUserTotal();

	/**
	 * 模糊查询
	 * @param searchTxt
	 * @param pageNum
	 * @return
	 */
	public List<TbUserEntity> searchUserByPage(String searchTxt, int pageNum);

	/**
	 * 检索的用户总数
	 * @return
	 */
	public int selectSearchUserTotal(String searchTxt);
}
