package com.wpc.dto;

import java.util.Date;

import com.wpc.entity.TbProgramEntity;
import com.wpc.entity.TbTheaterEntity;

import lombok.Data;

@Data
public class ProgramDto extends TbProgramEntity{
	private TbTheaterEntity theater;

	public ProgramDto(Long id, Long tid, String content, Date date, Date createtime, TbTheaterEntity theater) {
		super(id, tid, content, date, createtime);
		this.theater = theater;
	}
	
}
