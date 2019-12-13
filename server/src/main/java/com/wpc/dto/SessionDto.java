package com.wpc.dto;

import com.wpc.entity.TbActorEntity;
import com.wpc.entity.TbSessionEntity;
import com.wpc.entity.TbTheaterEntity;

import lombok.Data;

@Data
public class SessionDto extends TbSessionEntity {
	private TbTheaterEntity theater;
	private TbActorEntity starringActor1;
	private TbActorEntity starringActor2;
	private TbActorEntity assistantActor1;
	private TbActorEntity assistantActor2;
	private TbActorEntity assistantActor3;
	private TbActorEntity assistantActor4;
	private String sessionName;
	public SessionDto(TbSessionEntity entity, TbTheaterEntity theater,
			TbActorEntity starringActor1, TbActorEntity starringActor2, TbActorEntity assistantActor1,
			TbActorEntity assistantActor2, TbActorEntity assistantActor3, TbActorEntity assistantActor4,
			String sessionName) {
		super(entity.getId(), entity.getStarring1(), entity.getStarring2(), entity.getAssistant1(), entity.getAssistant2(), entity.getAssistant3(), entity.getAssistant4(), entity.getTid(), entity.getDate(), entity.getImg(), entity.getCreatetime());
		this.theater = theater;
		this.starringActor1 = starringActor1;
		this.starringActor2 = starringActor2;
		this.assistantActor1 = assistantActor1;
		this.assistantActor2 = assistantActor2;
		this.assistantActor3 = assistantActor3;
		this.assistantActor4 = assistantActor4;
		this.sessionName = sessionName;
	}
	
	
}
