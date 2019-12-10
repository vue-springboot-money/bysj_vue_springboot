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
		TbUserEntity checkResult = tbUserMapper.selectByUsername(entity.getUsername());

		// 如果存在重名用户，返回空
		if (checkResult != null) {
			return null;
		} else {
			// 不存在重名用户，对新建的用户的密码进行MD5加密并添加到数据库
			// 密码加密
			entity.setPassword(MD5Util.encode(entity.getPassword() == null ? "123456" : entity.getPassword()));

			// 设置昵称
			if (entity.getNickname() == null || entity.getNickname().equals("")) {
				entity.setNickname(entity.getUsername());
			}

			// 设置余额为0
			entity.setBalance((float) 0);

			// 设置身份为学生
			entity.setType((byte) 1);

			int insertResult = tbUserMapper.insert(entity);

			// 插入成功
			if (insertResult == 1) {
				return tbUserMapper.selectByUsername(entity.getUsername());
			} else {
				return null;
			}
		}

	}

	@Override
	@Transactional
	public int changePwd(Long id, String pwd) {

		// 密码加密
		pwd = MD5Util.encode(pwd);

		// 进行更新操作（返回值为1则更新成功）
		int updateResult = tbUserMapper.updatePwdById(id, pwd);

		return updateResult;
	}

	@Override
	public TbUserEntity findUserById(Long id) {
		// 根据id查询用户
		return tbUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public TbUserEntity updateUser(TbUserEntity entity) {
		int updateResult = tbUserMapper.updateByPrimaryKey(entity);

		if (updateResult == 1) {
			return entity;
		} else {
			return null;
		}
	}

	@Override
	public TbUserEntity recharge(TbUserEntity entity) {
		int updateResult = tbUserMapper.addBalanceById(entity);

		if (updateResult == 1) {
			return tbUserMapper.selectByPrimaryKey(entity.getId());
		} else {
			return null;
		}
	}

	@Override
	public TbUserEntity consume(TbUserEntity entity) {
		TbUserEntity user = tbUserMapper.selectByPrimaryKey(entity.getId());

		// 检查余额是否充足
		if (user.getBalance() - entity.getBalance() > 0) {
			int updateResult = tbUserMapper.reduceBalanceById(entity);
			if (updateResult == 1) {
				return tbUserMapper.selectByPrimaryKey(entity.getId());
			}
		}
		return null;

	}

	@Override
	public int deleteUser(Long id) {
		int deleteResult = tbUserMapper.deleteByPrimaryKey(id);
		return deleteResult;
	}

	@Override
	public List<TbUserEntity> selectUserListByPage(int pageNum) {
		// 每页显示的条数
		int count = 10;

		// 分页查询
		List<TbUserEntity> userList = tbUserMapper.selectByPage((pageNum - 1) * count, count);
		return userList;
	}

	@Override
	public int selectUserTotal() {
		return tbUserMapper.selectCount();
	}

	@Override
	public List<TbUserEntity> searchUserByPage(String searchTxt, int pageNum) {
		int count = 10;
		return tbUserMapper.selectBySearchAndPage(searchTxt, (pageNum - 1) * count, count);
	}

	@Override
	public int selectSearchUserTotal(String searchTxt) {
		return tbUserMapper.selectCountBySearch(searchTxt);
	}

	@Override
	public TbUserEntity getUserByUsername(String username) {
		return tbUserMapper.selectByUsername(username);
	}
}
