package com.ttcanteen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttcanteen.entity.TbUserEntity;
import com.ttcanteen.mapper.TbUserMapper;
import com.ttcanteen.service.UserService;
import com.ttcanteen.utils.MD5Util;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper tbUserMapper;

	@Override
	@Transactional
	public TbUserEntity createUser(TbUserEntity entity) {

		// 查看是否有重名用户
		List<TbUserEntity> checkResult = tbUserMapper.selectByUsername(entity.getUsername());

		// 如果存在重名用户，返回空
		if (checkResult.size() > 0) {
			return null;
		} else {
			// 不存在重名用户，对新建的用户的密码进行MD5加密并添加到数据库
			// 加密密码
			entity.setPassword(MD5Util.encode(entity.getPassword()));
			int insertResult = tbUserMapper.insert(entity);
			
			// 插入成功
			if (insertResult == 1) {
				return tbUserMapper.selectByUsername(entity.getUsername()).get(0);
			} else {
				return null;
			}
		}

	}
}
