package com.ttcanteen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ttcanteen.entity.TbOrderItemEntity;

@Mapper
public interface TbOrderItemMapper {
	@Insert("INSERT INTO tb_orderitem(`no`, mid, amount, price) VALUES (#{no}, #{mid}, #{amount}, #{price});")
	int insert(TbOrderItemEntity orderItemEntity);
	
	@Select("SELECT * FROM tb_orderitem WHERE no = #{no}")
	List<TbOrderItemEntity> selectByNo(String no);
}
