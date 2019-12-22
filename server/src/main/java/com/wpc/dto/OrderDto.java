package com.wpc.dto;

import com.wpc.entity.TbOrderEntity;
import com.wpc.entity.TbTicketEntity;
import com.wpc.entity.TbUserEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto extends TbOrderEntity{

	private TbUserEntity user;
	
	private TicketDto ticket;
	
	private List<TbTicketEntity> itemList;
	
	public OrderDto(Long id, String no, Long uid, Long tid, byte state, Date createtime, TbUserEntity user, TicketDto ticket) {
		super(id, no, uid, tid, state, createtime);
		this.user = user;
		this.ticket = ticket;
	}
	
}
