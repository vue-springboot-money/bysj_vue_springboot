package com.wpc.dto;

import java.util.Date;

import com.wpc.entity.TbActorEntity;
import com.wpc.entity.TbSessionEntity;
import com.wpc.entity.TbTheaterEntity;

import lombok.Data;

@Data
public class SessionDto extends TbSessionEntity {
	private TbTheaterEntity theater;
	private TbActorEntity starringActor;
	private TbActorEntity assistantActor;

	public SessionDto(Long id, Long starring, Long assistant, Long tid, Date date, String img, Date createtime,
			TbActorEntity starringActor, TbActorEntity assistantActor, TbTheaterEntity theater) {
		super(id, starring, assistant, tid, date, img, createtime);
		this.theater = theater;
		this.starringActor = starringActor;
		this.assistantActor = assistantActor;
	}
}
