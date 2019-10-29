package com.ttcanteen.service;

import com.ttcanteen.entity.TbUserEntity;

public interface UserService {

	/**
	 * 创建用户
	 * @param tbUser
	 * @return
	 */
	public TbUserEntity createUser(TbUserEntity entity);
}
