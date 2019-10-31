package com.ttcanteen.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.ttcanteen.entity.TbOrderItemEntity;

@Mapper
public interface TbOrderItemMapper {
	@Insert("INSERT INTO tb_orderitem(`no`, mid, amount, price) VALUES (#{no}, #{mid}, #{amount}, #{price});")
	int insert(TbOrderItemEntity orderItemEntity);
}
