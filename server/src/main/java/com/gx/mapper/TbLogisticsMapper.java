package com.gx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gx.entity.TbLogisticsEntity;

@Mapper
public interface TbLogisticsMapper {

	@Insert("INSERT INTO tb_logistics(oid, uid, state, content) VALUES(#{oid}, #{uid}, #{state}, #{content})")
	int insert(TbLogisticsEntity entity);

	@Select("SELECT * FROM tb_logistics where oid = #{oid}")
	TbLogisticsEntity selectByOid(Long oid);

	@Update("UPDATE tb_logistics SET state=#{state}, content=#{content} WHERE id=#{id};")
	int update(TbLogisticsEntity entity);

	@Select("SELECT * FROM tb_logistics where uid = #{uid}")
	List<TbLogisticsEntity> selectByUid(Long uid);

}
