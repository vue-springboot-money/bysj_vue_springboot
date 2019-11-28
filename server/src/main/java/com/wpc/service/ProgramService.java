package com.wpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wpc.entity.TbProgramEntity;
import com.wpc.mapper.TbProgramMapper;

@Service
public class ProgramService {

	@Autowired
	private TbProgramMapper tbProgramMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建节目单
	 * 
	 * @param entity
	 * @return
	 */
	public int createProgram(TbProgramEntity entity) {
		return tbProgramMapper.insert(entity);
	}

	/**
	 * 更新节目单信息
	 * 
	 * @param entity
	 * @return
	 */
	public int updateProgram(TbProgramEntity entity) {
		return tbProgramMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的节目单
	 * 
	 * @param id
	 * @return
	 */
	public TbProgramEntity getProgramById(Long id) {
		return tbProgramMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的节目单
	 * 
	 * @param id
	 * @return
	 */
	public int deleteProgramById(Long id) {
		return tbProgramMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<TbProgramEntity> getProgramListByPage(int page) {
		return tbProgramMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 查询节目单总数
	 * 
	 * @return
	 */
	public int getProgramCount() {
		return tbProgramMapper.selectCount();
	}

	/**
	 * 模糊查询
	 * @param search
	 * @param page
	 * @return
	 */
	public List<TbProgramEntity> getProgramListBySearchAndPage(String search, int page) {
		return tbProgramMapper.selectBySearchAndPage(search, (page - 1) * count, count);
	}

	/**
	 * 检索的节目单总数
	 * @param search
	 * @return
	 */
	public int getProgramCountBySearch(String search) {
		return tbProgramMapper.selectCountBySearch(search);
	}
}
