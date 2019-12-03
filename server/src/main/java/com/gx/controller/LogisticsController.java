package com.gx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gx.dto.LogisticsDto;
import com.gx.entity.TbLogisticsEntity;
import com.gx.pojo.Common;
import com.gx.pojo.ResultPojo;
import com.gx.service.LogisticsService;
import com.gx.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController()
@Api(description = "物流相关接口")
@Transactional
public class LogisticsController {

	@Autowired
	private LogisticsService orderService;

	@Autowired
	private UserService userService;


	@GetMapping("logistics/oid/{oid}")
	@ApiOperation("根据订单id查询快递员")
	public ResultPojo getLogisticsByOid(@PathVariable Long oid) {
		TbLogisticsEntity logistics = orderService.getLogisticsByOid(oid);
		LogisticsDto dto = new LogisticsDto();
		dto.setLogisticsEntity(logistics);
		dto.setUserEntity(userService.findUserById(logistics.getUid()));
		return new ResultPojo(Common.OK, dto);
	}
	
	@PatchMapping("logistics")
	@ApiOperation("更新物流信息")
	public ResultPojo getLogisticsByOid(@RequestBody TbLogisticsEntity entity) {
		int updateResult = orderService.update(entity);
		return new ResultPojo(Common.OK, updateResult);
	}
	
	@GetMapping("logistics/uid/{uid}/page/{page}")
	@ApiOperation("根据快递员id查询自己名下的物流订单")
	public ResultPojo getLogisticsByUid(@PathVariable Long uid, @PathVariable int page) {
		List<TbLogisticsEntity> selectResult = orderService.getLogisticsListByUidAndPage(uid, page);
		return new ResultPojo(Common.OK, selectResult);
	}
}
