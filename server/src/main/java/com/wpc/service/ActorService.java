package com.wpc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wpc.dto.ActorDto;
import com.wpc.entity.TbActorEntity;
import com.wpc.mapper.TbActorMapper;
import com.wpc.mapper.TbTeamMapper;

@Service
public class ActorService {

	@Autowired
	private TbActorMapper tbActorMapper;

	@Autowired
	private TbTeamMapper tbTeamMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建演员
	 * 
	 * @param entity
	 * @return
	 */
	public int createActor(TbActorEntity entity) {
		return tbActorMapper.insert(entity);
	}

	/**
	 * 更新演员信息
	 * 
	 * @param entity
	 * @return
	 */
	public int updateActor(TbActorEntity entity) {
		return tbActorMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的演员
	 * 
	 * @param id
	 * @return
	 */
	public TbActorEntity getActorById(Long id) {
		return tbActorMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的演员
	 * 
	 * @param id
	 * @return
	 */
	public int deleteActorById(Long id) {
		return tbActorMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @return
	 */
	public List<ActorDto> getActorListByPage(int page) {
		List<ActorDto> dtoResult = new ArrayList<>();
		List<TbActorEntity> selectResult = tbActorMapper.selectByPage((page - 1) * count, count);
		for (TbActorEntity entity : selectResult) {
			dtoResult.add(new ActorDto(entity.getId(), entity.getName(), entity.getImg(), entity.getInformation(),
					entity.getMasterpiece(), entity.getTid(), entity.getCreatetime(),
					tbTeamMapper.selectByPrimaryKey(entity.getTid())));
		}
		return dtoResult;
	}

	/**
	 * 查询演员总数
	 * 
	 * @return
	 */
	public int getActorCount() {
		return tbActorMapper.selectCount();
	}

	/**
	 * 模糊查询
	 * 
	 * @param search
	 * @param page
	 * @return
	 */
	public List<ActorDto> getActorListBySearchAndPage(String search, int page) {
		List<ActorDto> dtoResult = new ArrayList<>();
		List<TbActorEntity> selectResult = tbActorMapper.selectBySearchAndPage(search, (page - 1) * count, count);
		for (TbActorEntity entity : selectResult) {
			dtoResult.add(new ActorDto(entity.getId(), entity.getName(), entity.getImg(), entity.getInformation(),
					entity.getMasterpiece(), entity.getTid(), entity.getCreatetime(),
					tbTeamMapper.selectByPrimaryKey(entity.getTid())));
		}
		return dtoResult;
	}

	/**
	 * 检索的演员总数
	 * 
	 * @param search
	 * @return
	 */
	public int getActorCountBySearch(String search) {
		return tbActorMapper.selectCountBySearch(search);
	}

	public List<TbActorEntity> getActorList() {
		return tbActorMapper.selectAll();
	}
}
