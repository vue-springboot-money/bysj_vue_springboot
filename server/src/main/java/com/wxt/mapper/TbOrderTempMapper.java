package com.wxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wxt.entity.TbOrderTempEntity;

@Mapper
public interface TbOrderTempMapper {

	/**
	 * 插入
	 * 
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_ordertemp(did, mid, amount, state) VALUES(#{did}, #{mid}, #{amount}, #{state});")
	int insert(TbOrderTempEntity entity);

	/**
	 * 根据主键查询菜单
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_ordertemp WHERE id = #{id}")
	TbOrderTempEntity selectByPrimaryKey(Long id);

	/**
	 * 根据主键更新
	 * 
	 * @param id
	 * @return
	 */
	@Update("UPDATE tb_ordertemp SET did = #{did}, mid = #{mid}, amount = #{amount}, state = #{state} WHERE id = #{id}")
	int updateByPrimaryKey(TbOrderTempEntity entity);

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_ordertemp WHERE id = #{id}")
	int deleteByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * 
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_ordertemp LIMIT #{index}, #{count}")
	List<TbOrderTempEntity> selectByPage(int index, int count);

	/**
	 * 查询总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_ordertemp")
	int selectCount();
}
