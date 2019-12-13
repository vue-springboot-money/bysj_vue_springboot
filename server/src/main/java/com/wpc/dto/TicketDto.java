package com.wpc.dto;

import java.util.Date;

import com.wpc.entity.TbProgramEntity;
import com.wpc.entity.TbSessionEntity;
import com.wpc.entity.TbTicketEntity;

import lombok.Data;

@Data
public class TicketDto extends TbTicketEntity{
	private int num200;
	private int num400;
	private int num600;
	private int num800;
	private ProgramDto program;
	private SessionDto session;
	public TicketDto(Long id, Long pid, Long sid, float price, Integer num, Integer version, byte state,
			Date createtime, int num200, int num400, int num600, int num800, ProgramDto program,
			SessionDto session) {
		super(id, pid, sid, price, num, version, state, createtime);
		this.num200 = num200;
		this.num400 = num400;
		this.num600 = num600;
		this.num800 = num800;
		this.program = program;
		this.session = session;
	}
}
