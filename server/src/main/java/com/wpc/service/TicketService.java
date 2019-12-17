package com.wpc.service;

import com.wpc.dto.ProgramDto;
import com.wpc.dto.SessionDto;
import com.wpc.dto.TicketDto;
import com.wpc.entity.TbProgramEntity;
import com.wpc.entity.TbSessionEntity;
import com.wpc.entity.TbTicketEntity;
import com.wpc.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

	@Autowired
	private TbTicketMapper tbTicketMapper;

	@Autowired
	private TbProgramMapper tbProgramMapper;

	@Autowired
	private TbSessionMapper tbSessionMapper;

	@Autowired
	private TbTheaterMapper tbTheaterMapper;

	@Autowired
	private TbActorMapper tbActorMapper;

	@Value("${default.count}")
	private int count;

    /**
     * 创建门票
     *
     * @param dto
     * @return
     */
	public int createTicket(TicketDto dto) {
		Long pid = dto.getPid();
		Long sid = dto.getSid();
		if (pid == null) {
			int num200 = dto.getNum200();
			int num400 = dto.getNum400();
			int num600 = dto.getNum600();
			int num800 = dto.getNum800();
			TbTicketEntity entity = new TbTicketEntity();
			entity.setSid(sid);
			entity.setPrice(200);
			entity.setNum(num200);
			entity.setVersion(0);
			entity.setState((byte) 0);
			tbTicketMapper.insertS(entity);
			entity.setPrice(400);
			entity.setNum(num400);
			tbTicketMapper.insertS(entity);
			entity.setPrice(600);
			entity.setNum(num600);
			tbTicketMapper.insertS(entity);
			entity.setPrice(800);
			entity.setNum(num800);
			tbTicketMapper.insertS(entity);
		}
		if (sid == null) {
			int num200 = dto.getNum200();
			int num400 = dto.getNum400();
			int num600 = dto.getNum600();
			int num800 = dto.getNum800();
			TbTicketEntity entity = new TbTicketEntity();
			entity.setPid(pid);
			entity.setPrice(200);
			entity.setNum(num200);
			entity.setVersion(0);
			entity.setState((byte) 0);
			tbTicketMapper.insertP(entity);
			entity.setPrice(400);
			entity.setNum(num400);
			tbTicketMapper.insertP(entity);
			entity.setPrice(600);
			entity.setNum(num600);
			tbTicketMapper.insertP(entity);
			entity.setPrice(800);
			entity.setNum(num800);
			tbTicketMapper.insertP(entity);
		}
		return 1;
	}

	/**
	 * 更新门票信息
	 * 
	 * @param entity
	 * @return
	 */
	public int updateTicket(TbTicketEntity entity) {
		return tbTicketMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的门票
	 * 
	 * @param id
	 * @return
	 */
	public TbTicketEntity getTicketById(Long id) {
		return tbTicketMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的门票
	 * 
	 * @param id
	 * @return
	 */
	public int deleteTicketById(Long id) {
		return tbTicketMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询非专场
	 * 
	 * @param page
	 * @return
	 */
	public List<TbTicketEntity> getProgramTicketListByPage(int page) {
		return tbTicketMapper.selectProgramTicketByPage((page - 1) * count, count);
	}

	/**
	 * 获取所有非专场门票数量
	 * 
	 * @return
	 */
	public int getProgramTicketCount() {
		return tbTicketMapper.selectProgramTicketCount();
	}

	/**
	 * 分页查询专场
	 * 
	 * @param page
	 * @return
	 */
	public List<TbTicketEntity> getSessionTicketListByPage(int page) {
		return tbTicketMapper.selectSessionTicketByPage((page - 1) * count, count);
	}

	/**
	 * 获取所有专场门票数量
	 * 
	 * @return
	 */
	public int getSessionTicketCount() {
		return tbTicketMapper.selectSessionTicketCount();
	}

	/**
	 * 查询非专场的票价
	 * 
	 * @param pid
	 * @return
	 */
	public List<TbTicketEntity> getProgramTicketListByPid(Long pid) {
		return tbTicketMapper.selectByPid(pid);
    }

    /**
     * 查询专场的票价
     *
     * @param sid
     * @return
	 */
	public List<TbTicketEntity> getProgramTicketListBySid(Long sid) {
		return tbTicketMapper.selectBySid(sid);
	}

	public List<TicketDto> getSessionTicket() {
		List<TbTicketEntity> selectResult = tbTicketMapper.selectSessionTicket();
		List<TicketDto> dtoList = new ArrayList<TicketDto>();
		for (TbTicketEntity entity : selectResult) {
			TbSessionEntity tbSessionEntity = tbSessionMapper.selectByPrimaryKey(entity.getSid());
			SessionDto dto = new SessionDto(tbSessionEntity,
					tbTheaterMapper.selectByPrimaryKey(tbSessionEntity.getTid()),
					tbActorMapper.selectByPrimaryKey(tbSessionEntity.getStarring1()),
					tbActorMapper.selectByPrimaryKey(tbSessionEntity.getStarring2()),
					tbActorMapper.selectByPrimaryKey(tbSessionEntity.getAssistant1()),
					tbActorMapper.selectByPrimaryKey(tbSessionEntity.getAssistant2()),
					tbActorMapper.selectByPrimaryKey(tbSessionEntity.getAssistant3()),
					tbActorMapper.selectByPrimaryKey(tbSessionEntity.getAssistant4()),
					tbActorMapper.selectByPrimaryKey(tbSessionEntity.getStarring1()).getName()
							+ tbActorMapper.selectByPrimaryKey(tbSessionEntity.getStarring2()).getName() + "相声专场");

			dtoList.add(
					new TicketDto(entity.getId(), entity.getPid(), entity.getSid(), entity.getPrice(), entity.getNum(),
							entity.getVersion(), entity.getState(), entity.getCreatetime(), 0, 0, 0, 0, null, dto));

		}
		return dtoList;
	}

	public List<TicketDto> getProgramTicket() {
		List<TbTicketEntity> selectResult = tbTicketMapper.selectProgramTicket();
		List<TicketDto> dtoList = new ArrayList<TicketDto>();
		for (TbTicketEntity entity : selectResult) {
			TbProgramEntity tbProgramEntity = tbProgramMapper.selectByPrimaryKey(entity.getPid());
			ProgramDto programDto = new ProgramDto(tbProgramEntity.getId(), tbProgramEntity.getTid(),
					tbProgramEntity.getContent(), tbProgramEntity.getDate(), tbProgramEntity.getCreatetime(),
					tbTheaterMapper.selectByPrimaryKey(tbProgramEntity.getTid()));
			dtoList.add(new TicketDto(entity.getId(), entity.getPid(), entity.getSid(), entity.getPrice(),
					entity.getNum(), entity.getVersion(), entity.getState(), entity.getCreatetime(), 0, 0, 0, 0,
					programDto, null));

		}
		return dtoList;
	}
}
