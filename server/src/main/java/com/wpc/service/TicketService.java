package com.wpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wpc.entity.TbTicketEntity;
import com.wpc.mapper.TbTicketMapper;

@Service
public class TicketService {

	@Autowired
	private TbTicketMapper tbTicketMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建门票
	 * 
	 * @param entity
	 * @return
	 */
	public int createTicket(TbTicketEntity entity) {
		return tbTicketMapper.insert(entity);
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
	 * @param pid
	 * @return
	 */
	public List<TbTicketEntity> getProgramTicketListByPid(Long pid) {
		return tbTicketMapper.selectByPid(pid);
	}
	
	/**
	 * 查询专场的票价
	 * @param pid
	 * @return
	 */
	public List<TbTicketEntity> getProgramTicketListBySid(Long sid) {
		return tbTicketMapper.selectBySid(sid);
	}
}
