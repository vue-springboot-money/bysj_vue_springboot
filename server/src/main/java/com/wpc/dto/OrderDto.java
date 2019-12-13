package com.wpc.dto;

import java.util.Date;

import com.wpc.entity.TbOrderEntity;
import com.wpc.entity.TbUserEntity;

import lombok.Data;

@Data
public class OrderDto extends TbOrderEntity{

	private TbUserEntity user;
	
	private TicketDto ticket;

	public OrderDto(Long id, Long uid, Long tid, byte state, Date createtime, TbUserEntity user, TicketDto ticket) {
		super(id, uid, tid, state, createtime);
		this.user = user;
		this.ticket = ticket;
	}
}
