package com.wpc.dto;

import java.util.Date;

import com.wpc.entity.TbActorEntity;
import com.wpc.entity.TbTeamEntity;

import lombok.Data;

@Data
public class ActorDto extends TbActorEntity{
	private TbTeamEntity team;

	public ActorDto(Long id, String name, String img, String information, String masterpiece, Long tid, Date createtime,
			TbTeamEntity team) {
		super(id, name, img, information, masterpiece, tid, createtime);
		this.team = team;
	}
	
	
}
