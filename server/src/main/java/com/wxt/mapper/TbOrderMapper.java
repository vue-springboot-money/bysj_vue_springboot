package com.wxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wxt.entity.TbOrderEntity;

@Mapper
public interface TbOrderMapper {

	/**
	 * 插入
	 * 
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_order(`no`, did, price, state) VALUES(#{no}, #{did}, #{price}, #{state});")
	int insert(TbOrderEntity entity);

	/**
	 * 根据主键查询菜单
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_order WHERE id = #{id}")
	TbOrderEntity selectByPrimaryKey(Long id);

	/**
	 * 根据主键更新
	 * 
	 * @param id
	 * @return
	 */
	@Update("UPDATE tb_order SET `no` = #{no}, did = #{did}, price = #{price}, state = #{state} WHERE id = #{id}")
	int updateByPrimaryKey(TbOrderEntity entity);

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_order WHERE id = #{id}")
	int deleteByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * 
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_order LIMIT #{index}, #{count}")
	List<TbOrderEntity> selectByPage(int index, int count);

	/**
	 * 查询总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_order")
	int selectCount();
}
