package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.entity.TbUser;

@Mapper
public interface TbUserMapper {
	
	@Select("SELECT id, username, password, `type` FROM tb_user")
	List<TbUser> selectAll();

	@Select("SELECT id, username, password, `type` FROM tb_user WHERE id = #{id}")
	TbUser selectById(Long id);
	
	@Select("SELECT id, username, password, `type` FROM tb_user WHERE username = #{username}")
	List<TbUser> selectByUsername(String username);
	
	@Select("SELECT id, username, password, `type` FROM tb_user WHERE `type` = #{type}")
	List<TbUser> selectByType(byte type);
	
	@Insert("INSERT INTO tb_user(username, password, `type`) VALUES(#{username}, #{password}, #{type})")
	int insert(TbUser tbUser);
	
	@Delete("DELETE FROM tb_user WHERE id = #{id}")
	int deleteById(Long id);
	
	@Delete("DELETE FROM tb_user WHERE `type` = #{type}")	
	int deleteByType(byte type);
	
	@Delete("DELETE FROM tb_user WHERE username = #{username}")	
	int deleteByUsername(String username);
}
