package com.ttcanteen.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ttcanteen.entity.TbOrderEntity;

@Mapper
public interface TbOrderMapper {

	@Select("SELECT COUNT(1) FROM tb_order WHERE code = #{code};")
	int selectByCode(String code);

	@Insert("INSERT INTO tb_order (`no`, uid, price, `comment`, state, `code`) VALUES (#{no}, #{uid}, #{price}, #{comment}, #{state}, #{code}, );")
	int insert(TbOrderEntity orderEntity);

}
