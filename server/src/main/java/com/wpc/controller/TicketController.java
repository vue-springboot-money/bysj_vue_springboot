package com.wpc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wpc.entity.TbTicketEntity;
import com.wpc.pojo.Common;
import com.wpc.pojo.ResultPojo;
import com.wpc.service.TicketService;

@RestController()
@Transactional
public class TicketController {

	@Autowired
	private TicketService ticketService;

	/**
	 * 创建门票
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("ticket")
	public ResultPojo createTicket(@RequestBody TbTicketEntity entity) {

		int result = ticketService.createTicket(entity);

		// 注册失败
		if (result != 1) {
			return new ResultPojo(Common.ERR, entity);
		} else {
			// 注册成功
			return new ResultPojo(Common.OK, entity);
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
	@GetMapping("ticket/{id}")
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
	@DeleteMapping("ticket/{id}")
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
	 * @param pageNum
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
	 * @param pageNum
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
	 * @param pageNum
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
	 * @param pageNum
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
}
