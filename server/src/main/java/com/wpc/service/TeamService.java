package com.wpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wpc.entity.TbTeamEntity;
import com.wpc.mapper.TbTeamMapper;

@Service
public class TeamService {

	@Autowired
	private TbTeamMapper tbTeamMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建队伍
	 * 
	 * @param entity
	 * @return
	 */
	public int createTeam(TbTeamEntity entity) {
		return tbTeamMapper.insert(entity);
	}

	/**
	 * 更新队伍信息
	 * 
	 * @param entity
	 * @return
	 */
	public int updateTeam(TbTeamEntity entity) {
		return tbTeamMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的队伍
	 * 
	 * @param id
	 * @return
	 */
	public TbTeamEntity getTeamById(Long id) {
		return tbTeamMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的队伍
	 * 
	 * @param id
	 * @return
	 */
	public int deleteTeamById(Long id) {
		return tbTeamMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<TbTeamEntity> getTeamListByPage(int page) {
		return tbTeamMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 查询队伍总数
	 * 
	 * @return
	 */
	public int getTeamCount() {
		return tbTeamMapper.selectCount();
	}

	/**
	 * 模糊查询
	 * @param search
	 * @param page
	 * @return
	 */
	public List<TbTeamEntity> getTeamListBySearchAndPage(String search, int page) {
		return tbTeamMapper.selectBySearchAndPage(search, (page - 1) * count, count);
	}

	/**
	 * 检索的队伍总数
	 * @param search
	 * @return
	 */
	public int getTeamCountBySearch(String search) {
		return tbTeamMapper.selectCountBySearch(search);
	}

	/**
	 * 全部检索
	 * @return
	 */
	public List<TbTeamEntity> getTeamList() {
		return tbTeamMapper.selectAll();
	}
}
