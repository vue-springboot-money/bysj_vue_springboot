package com.wpc.controller;

import java.util.ArrayList;
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

import com.wpc.dto.SessionDto;
import com.wpc.entity.TbSessionEntity;
import com.wpc.pojo.Common;
import com.wpc.pojo.ResultPojo;
import com.wpc.service.ActorService;
import com.wpc.service.SessionService;
import com.wpc.service.TheaterService;

@RestController()
@Transactional
public class SessionController {

	@Autowired
	private SessionService sessionService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private TheaterService theaterService;

	/**
	 * 创建专场
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping("session")
	public ResultPojo createSession(@RequestBody TbSessionEntity entity) {

		int result = sessionService.createSession(entity);

		// 注册失败
		if (result != 1) {
			return new ResultPojo(Common.ERR, entity);
		} else {
			// 注册成功
			return new ResultPojo(Common.OK, entity);
		}
	}

	/**
	 * 更新专场
	 * 
	 * @param entity
	 * @return
	 */
	@PatchMapping("session")
	public ResultPojo modifySession(@RequestBody TbSessionEntity entity) {
		int updateResult = sessionService.updateSession(entity);

		// 更新成功
		if (updateResult != 0) {
			return new ResultPojo(Common.OK, entity);
		} else {
			// 更新失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 查询指定id的专场
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("session/id/{id}")
	public ResultPojo getSessionById(@PathVariable Long id) {
		TbSessionEntity result = sessionService.getSessionById(id);

		// 查询成功
		if (result != null) {
			return new ResultPojo(Common.OK, result);
		} else {
			// 查询失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 删除指定id的专场
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("session/id/{id}")
	public ResultPojo deleteSession(@PathVariable Long id) {
		int deleteResult = sessionService.deleteSessionById(id);

		// 删除成功
		if (deleteResult == 1) {
			return new ResultPojo(Common.OK, deleteResult);
		} else {
			// 删除失败
			return new ResultPojo(Common.ERR, null);
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @return
	 */
	@GetMapping("session/page/{page}")
	public ResultPojo getSessionListByPage(@PathVariable int page) {
		List<TbSessionEntity> sessionList = sessionService.getSessionListByPage(page);

		// 查询成功
		if (sessionList == null || sessionList.size() == 0) {
			return new ResultPojo(Common.ERR, null);
		} else {
			// 查询失败（没有数据）
			List<SessionDto> dtoList = new ArrayList<>();
			for (TbSessionEntity entity : sessionList) {
				dtoList.add(new SessionDto(entity, theaterService.getTheaterById(entity.getTid()),
						actorService.getActorById(entity.getStarring1()),
						actorService.getActorById(entity.getStarring2()),
						actorService.getActorById(entity.getAssistant1()),
						actorService.getActorById(entity.getAssistant2()),
						actorService.getActorById(entity.getAssistant3()),
						actorService.getActorById(entity.getAssistant4()),
						actorService.getActorById(entity.getStarring1()).getName()
								+ actorService.getActorById(entity.getStarring2()).getName() + "相声专场"));
			}
			return new ResultPojo(Common.OK, dtoList);
		}
	}

	/**
	 * 获取所有专场数量
	 * 
	 * @return
	 */
	@GetMapping("session/count")
	public ResultPojo getSessionCount() {
		return new ResultPojo(Common.OK, sessionService.getSessionCount());
	}
	
	/**
	 * 获取符合检索条件的所有专场
	 * @param searchTxt
	 * @return
	 */
	@GetMapping("session/ticket")
	public ResultPojo getProgramForTicket() {
		return new ResultPojo(Common.OK, sessionService.getSessionForTicket());
	}
}
