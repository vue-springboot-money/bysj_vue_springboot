package com.ttcanteen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ttcanteen.entity.TbUserEntity;

@Mapper
public interface TbUserMapper {

	@Select("SELECT * FROM tb_user WHERE username = #{username};")
	List<TbUserEntity> selectByUsername(String username);

	@Insert("INSERT INTO tb_user(username, passwrod, nickname, sex, `type`, balance) VALUES(#{username}, #{password}, #{nickname}, #{sex}, #{type}, #{balance});")
	int insert(TbUserEntity entity);
}
