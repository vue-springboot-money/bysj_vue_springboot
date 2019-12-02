package com.gx.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.gx.entity.TbLogisticsEntity;

@Mapper
public interface TbLogisticsMapper {

	@Insert("INSERT INTO tb_logistics(oid, uid, state, content) VALUES(#{oid}, #{uid}, #{state}, #{content})")
	int insert(TbLogisticsEntity entity);

	@Select("SELECT * FROM tb_logistics where oid = #{oid}")
	TbLogisticsEntity selectByOid(Long oid);

}
