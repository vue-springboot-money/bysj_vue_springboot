package com.wpc.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wpc.dto.SessionDto;
import com.wpc.entity.TbSessionEntity;
import com.wpc.mapper.TbActorMapper;
import com.wpc.mapper.TbSessionMapper;
import com.wpc.mapper.TbTheaterMapper;

@Service
public class SessionService {

	@Autowired
	private TbSessionMapper tbSessionMapper;
	@Autowired
	private TbTheaterMapper tbTheaterMapper;
	@Autowired
	private TbActorMapper tbActorMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建专场
	 * 
	 * @param entity
	 * @return
	 */
	public int createSession(TbSessionEntity entity) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(entity.getDate());
		calendar.add(Calendar.DATE, 1);
		entity.setDate(calendar.getTime());
		return tbSessionMapper.insert(entity);
	}

	/**
	 * 更新专场信息
	 * 
	 * @param entity
	 * @return
	 */
	public int updateSession(TbSessionEntity entity) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(entity.getDate());
		calendar.add(Calendar.DATE, 1);
		entity.setDate(calendar.getTime());
		return tbSessionMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的专场
	 * 
	 * @param id
	 * @return
	 */
	public TbSessionEntity getSessionById(Long id) {
		return tbSessionMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的专场
	 * 
	 * @param id
	 * @return
	 */
	public int deleteSessionById(Long id) {
		return tbSessionMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @return
	 */
	public List<TbSessionEntity> getSessionListByPage(int page) {
		return tbSessionMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 查询专场总数
	 * 
	 * @return
	 */
	public int getSessionCount() {
		return tbSessionMapper.selectCount();
	}

	/**
	 * 获取需要设置票价的专场
	 * 
	 * @return
	 */
	public List<SessionDto> getSessionForTicket() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<TbSessionEntity> selectResult = tbSessionMapper.selectByDate(sdf.format(new Date()));
		List<SessionDto> dtoList = new ArrayList<SessionDto>();
		for (TbSessionEntity entity : selectResult) {
			dtoList.add(new SessionDto(entity, tbTheaterMapper.selectByPrimaryKey(entity.getTid()),
					tbActorMapper.selectByPrimaryKey(entity.getStarring1()),
					tbActorMapper.selectByPrimaryKey(entity.getStarring2()),
					tbActorMapper.selectByPrimaryKey(entity.getAssistant1()),
					tbActorMapper.selectByPrimaryKey(entity.getAssistant2()),
					tbActorMapper.selectByPrimaryKey(entity.getAssistant3()),
					tbActorMapper.selectByPrimaryKey(entity.getAssistant4()),
					tbActorMapper.selectByPrimaryKey(entity.getStarring1()).getName()
							+ tbActorMapper.selectByPrimaryKey(entity.getStarring2()).getName() + "相声专场"));
		}
		return dtoList;
	}
}
