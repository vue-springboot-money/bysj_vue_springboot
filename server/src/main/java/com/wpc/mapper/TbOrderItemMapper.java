package com.wpc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wpc.entity.TbOrderItemEntity;

@Mapper
public interface TbOrderItemMapper {

	/**
	 * 插入
	 * 
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_orderitem(`no`, `mid`, amount, price) VALUES(#{no}, #{mid}, #{amount}, #{price});")
	int insert(TbOrderItemEntity entity);

	/**
	 * 根据主键查询菜单
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_orderitem WHERE id = #{id}")
	TbOrderItemEntity selectByPrimaryKey(Long id);

	/**
	 * 根据主键更新
	 * 
	 * @param id
	 * @return
	 */
	@Update("UPDATE tb_orderitem SET `no` = #{no}, mid = #{mid}, amount = #{amount}, price = #{price} WHERE id = #{id}")
	int updateByPrimaryKey(TbOrderItemEntity entity);

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_orderitem WHERE id = #{id}")
	int deleteByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * 
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_orderitem LIMIT #{index}, #{count}")
	List<TbOrderItemEntity> selectByPage(int index, int count);

	/**
	 * 查询总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_orderitem")
	int selectCount();
}
