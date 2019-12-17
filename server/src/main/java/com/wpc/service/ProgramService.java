package com.wpc.service;

import com.wpc.dto.ProgramDto;
import com.wpc.entity.TbProgramEntity;
import com.wpc.mapper.TbProgramMapper;
import com.wpc.mapper.TbTheaterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ProgramService {

	@Autowired
	private TbProgramMapper tbProgramMapper;

	@Autowired
	private TbTheaterMapper tbTheaterMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建节目单
	 * 
	 * @param entity
	 * @return
	 */
	public int createProgram(TbProgramEntity entity) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(entity.getDate());
		calendar.add(Calendar.DATE, 1);
		entity.setDate(calendar.getTime());
		return tbProgramMapper.insert(entity);
	}

	/**
	 * 更新节目单信息
	 * 
	 * @param entity
	 * @return
	 */
	public int updateProgram(TbProgramEntity entity) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(entity.getDate());
		calendar.add(Calendar.DATE, 1);
		entity.setDate(calendar.getTime());
		return tbProgramMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的节目单
	 * 
	 * @param id
	 * @return
	 */
	public ProgramDto getProgramById(Long id) {
		TbProgramEntity selectResult = tbProgramMapper.selectByPrimaryKey(id);
		ProgramDto programDto = new ProgramDto(selectResult.getId(), selectResult.getTid(), selectResult.getContent(),
				selectResult.getDate(), selectResult.getCreatetime(),
				tbTheaterMapper.selectByPrimaryKey(selectResult.getTid()));
		return programDto;
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
	 * 
	 * @param page
	 * @return
	 */
	public List<ProgramDto> getProgramListByPage(int page) {
		List<ProgramDto> dtoList = new ArrayList<>();
		List<TbProgramEntity> selectResult = tbProgramMapper.selectByPage((page - 1) * count, count);
		for (TbProgramEntity entity : selectResult) {
			dtoList.add(new ProgramDto(entity.getId(), entity.getTid(), entity.getContent(), entity.getDate(),
					entity.getCreatetime(), tbTheaterMapper.selectByPrimaryKey(entity.getId())));
		}
		return dtoList;
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
	 * 
	 * @param search
	 * @param page
	 * @return
	 */
	public List<TbProgramEntity> getProgramListBySearchAndPage(String search, int page) {
		return tbProgramMapper.selectBySearchAndPage(search, (page - 1) * count, count);
	}

	/**
	 * 检索的节目单总数
	 * 
	 * @param search
	 * @return
	 */
	public int getProgramCountBySearch(String search) {
		return tbProgramMapper.selectCountBySearch(search);
	}

	/**
	 * 获取需要设置票价的节目单
	 * 
	 * @return
	 */
	public List<ProgramDto> getProgramForTicket() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<TbProgramEntity> selectResult = tbProgramMapper.selectByDate(sdf.format(new Date()));
		List<ProgramDto> dtoList = new ArrayList<ProgramDto>();
		for (TbProgramEntity entity : selectResult) {
			dtoList.add(new ProgramDto(entity.getId(), entity.getTid(), entity.getContent(), entity.getDate(),
					entity.getCreatetime(), tbTheaterMapper.selectByPrimaryKey(entity.getTid())));
		}
		return dtoList;
	}

	/**
	 * 根据剧场id获取节目单列表
	 *
	 * @param tid
	 * @return
	 */
	public List<TbProgramEntity> getProgramListByTid(Long tid) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return tbProgramMapper.selectByTid(tid, sdf.format(date));
	}
}
