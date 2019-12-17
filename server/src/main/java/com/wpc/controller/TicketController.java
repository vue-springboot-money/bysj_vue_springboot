package com.wpc.controller;

import com.wpc.dto.TicketDto;
import com.wpc.entity.TbTicketEntity;
import com.wpc.pojo.Common;
import com.wpc.pojo.ResultPojo;
import com.wpc.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@Transactional
public class TicketController {

	@Autowired
	private TicketService ticketService;

	/**
	 * 创建门票
	 *
	 * @param dto
	 * @return
	 */
	@PostMapping("ticket")
	public ResultPojo createTicket(@RequestBody TicketDto dto) {

		int result = ticketService.createTicket(dto);

		// 注册失败
		if (result != 1) {
			return new ResultPojo(Common.ERR, result);
		} else {
			// 注册成功
			return new ResultPojo(Common.OK, result);
		}
	}

	/**
	 * 更新门票
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("ticket")
	public ResultPojo modifyTicket(@RequestBody TbTicketEntity entity) {
		int updateResult = ticketService.updateTicket(entity);

		// 更新成功
		if (updateResult != 0) {
			return new ResultPojo(Common.OK, entity);
		} else {
			// 更新失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 查询指定id的门票
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("ticket/id/{id}")
	public ResultPojo getTicketById(@PathVariable Long id) {
		TbTicketEntity result = ticketService.getTicketById(id);

		// 查询成功
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			// 查询失败
			return new ResultPojo(Common.ERR, null);
		}
	}
	
	/**
	 * 删除指定id的门票
	 * @param id
	 * @return
	 */
	@DeleteMapping("ticket/id/{id}")
	public ResultPojo deleteTicket(@PathVariable Long id) {
		int deleteResult = ticketService.deleteTicketById(id);

		// 删除成功
		if (deleteResult == 1) {
			return new ResultPojo(Common.OK, deleteResult);
		} else {
			// 删除失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 分页查询非专场
	 * @param page
	 * @return
	 */
	@GetMapping("ticket/program/page/{page}")
	public ResultPojo getProgramTicketListByPage(@PathVariable int page) {
		List<TbTicketEntity> ticketList = ticketService.getProgramTicketListByPage(page);

		// 查询成功
		if (ticketList == null || ticketList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, ticketList);
		}
	}

	/**
	 * 获取所有非专场门票数量
	 * @return
	 */
	@GetMapping("ticket/program/count")
	public ResultPojo getProgramTicketCount() {
		return new ResultPojo(Common.OK, ticketService.getProgramTicketCount());
	}

	/**
	 * 分页查询专场
	 * @param page
	 * @return
	 */
	@GetMapping("ticket/session/page/{page}")
	public ResultPojo getSessionTicketListByPage(@PathVariable int page) {
		List<TbTicketEntity> ticketList = ticketService.getSessionTicketListByPage(page);

		// 查询成功
		if (ticketList == null || ticketList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, ticketList);
		}
	}

	/**
	 * 获取所有专场门票数量
	 * @return
	 */
	@GetMapping("ticket/session/count")
	public ResultPojo getSessionTicketCount() {
		return new ResultPojo(Common.OK, ticketService.getSessionTicketCount());
	}

	/**
	 * 根据非专场id查询非专场
	 * @param pid
	 * @return
	 */
	@GetMapping("ticket/pid/{pid}")
	public ResultPojo getProgramTicketListByPid(@PathVariable Long pid) {
		List<TbTicketEntity> ticketList = ticketService.getProgramTicketListByPid(pid);

		// 查询成功
		if (ticketList == null || ticketList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, ticketList);
		}
	}

	/**
	 * 根据专场id查询专场
	 * @param sid
	 * @return
	 */
	@GetMapping("ticket/sid/{sid}")
	public ResultPojo getProgramTicketListBySid(@PathVariable Long sid) {
		List<TbTicketEntity> ticketList = ticketService.getProgramTicketListBySid(sid);

		// 查询成功
		if (ticketList == null || ticketList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, ticketList);
		}
	}
	
	/**
	 * 获取专场门票
	 * @param 
	 * @return
	 */
	@GetMapping("ticket/s")
	public ResultPojo getSessionTicket() {
		List<TicketDto> ticketList = ticketService.getSessionTicket();

		// 查询成功
		if (ticketList == null || ticketList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, ticketList);
		}
	}
	
	/**
	 * 获取非专场门票
	 * @param 
	 * @return
	 */
	@GetMapping("ticket/p")
	public ResultPojo getProgramTicket() {
		List<TicketDto> ticketList = ticketService.getProgramTicket();

		// 查询成功
		if (ticketList == null || ticketList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			return new ResultPojo(Common.OK, ticketList);
		}
	}

	/**
	 * 根据剧场id获取剧场下所有在售的场次的日期及票价与数量
	 *
	 * @return
	 */
	@GetMapping("dys/ticket/pid/{pid}")
	public ResultPojo getProgramTicketByTid(@PathVariable Long pid) {
		List<TbTicketEntity> ticketList = ticketService.getProgramTicketListByPid(pid);
		return new ResultPojo(Common.OK, ticketList);
	}
}
