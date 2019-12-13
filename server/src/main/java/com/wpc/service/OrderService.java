package com.wpc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wpc.dto.OrderDto;
import com.wpc.dto.ProgramDto;
import com.wpc.dto.SessionDto;
import com.wpc.dto.TicketDto;
import com.wpc.entity.TbOrderEntity;
import com.wpc.entity.TbProgramEntity;
import com.wpc.entity.TbSessionEntity;
import com.wpc.entity.TbTicketEntity;
import com.wpc.mapper.TbActorMapper;
import com.wpc.mapper.TbOrderMapper;
import com.wpc.mapper.TbProgramMapper;
import com.wpc.mapper.TbSessionMapper;
import com.wpc.mapper.TbTheaterMapper;
import com.wpc.mapper.TbTicketMapper;
import com.wpc.mapper.TbUserMapper;

@Service
public class OrderService {

	@Autowired
	private TbOrderMapper tbOrderMapper;
	@Autowired
	private TbUserMapper tbUserMapper;
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
	 * 创建订单
	 * 
	 * @param entity
	 * @return
	 */
	public int createOrder(TbOrderEntity entity) {
		return tbOrderMapper.insert(entity);
	}

	/**
	 * 更新订单信息
	 * 
	 * @param entity
	 * @return
	 */
	public int updateOrder(TbOrderEntity entity) {
		return tbOrderMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的订单
	 * 
	 * @param id
	 * @return
	 */
	public TbOrderEntity getOrderById(Long id) {
		return tbOrderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的订单
	 * 
	 * @param id
	 * @return
	 */
	public int deleteOrderById(Long id) {
		return tbOrderMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @return
	 */
	public List<TbOrderEntity> getOrderListByPage(int page) {
		return tbOrderMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 查询订单总数
	 * 
	 * @return
	 */
	public int getOrderCount() {
		return tbOrderMapper.selectCount();
	}

	/**
	 * 根据用户id查询订单
	 * 
	 * @param uid
	 * @return
	 */
	public List<OrderDto> getOrderListByUid(Long uid) {

		List<TbOrderEntity> selectResult = tbOrderMapper.selectByUid(uid);

		List<OrderDto> dtoList = new ArrayList<OrderDto>();
		for (TbOrderEntity entity : selectResult) {

			TbTicketEntity ticketEntity = tbTicketMapper.selectByPrimaryKey(entity.getTid());
			TbProgramEntity tbProgramEntity = tbProgramMapper.selectByPrimaryKey(ticketEntity.getPid());
			ProgramDto programDto = new ProgramDto(tbProgramEntity.getId(), tbProgramEntity.getTid(),
					tbProgramEntity.getContent(), tbProgramEntity.getDate(), tbProgramEntity.getCreatetime(),
					tbTheaterMapper.selectByPrimaryKey(tbProgramEntity.getTid()));
			TbSessionEntity tbSessionEntity = tbSessionMapper.selectByPrimaryKey(ticketEntity.getSid());
			SessionDto sessionDto = new SessionDto(tbSessionEntity,
					tbTheaterMapper.selectByPrimaryKey(tbSessionEntity.getTid()),
					tbActorMapper.selectByPrimaryKey(tbSessionEntity.getStarring1()),
					tbActorMapper.selectByPrimaryKey(tbSessionEntity.getStarring2()),
					tbActorMapper.selectByPrimaryKey(tbSessionEntity.getAssistant1()),
					tbActorMapper.selectByPrimaryKey(tbSessionEntity.getAssistant2()),
					tbActorMapper.selectByPrimaryKey(tbSessionEntity.getAssistant3()),
					tbActorMapper.selectByPrimaryKey(tbSessionEntity.getAssistant4()),
					tbActorMapper.selectByPrimaryKey(tbSessionEntity.getStarring1()).getName()
							+ tbActorMapper.selectByPrimaryKey(tbSessionEntity.getStarring2()).getName() + "相声专场");

			dtoList.add(new OrderDto(entity.getId(), entity.getUid(), entity.getTid(), entity.getState(),
					entity.getCreatetime(), tbUserMapper.selectByPrimaryKey(entity.getUid()),
					new TicketDto(ticketEntity.getId(), ticketEntity.getPid(), ticketEntity.getSid(),
							ticketEntity.getPrice(), ticketEntity.getNum(), ticketEntity.getVersion(),
							ticketEntity.getState(), ticketEntity.getCreatetime(), 0, 0, 0, 0, programDto,
							sessionDto)));
		}

		return dtoList;
	}
}
