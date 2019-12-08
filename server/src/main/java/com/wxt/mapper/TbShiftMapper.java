package com.wxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wxt.entity.TbShiftEntity;

@Mapper
public interface TbShiftMapper {

	/**
	 * 插入
	 * 
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_shift(uid) VALUES(#{uid});")
	int insert(TbShiftEntity entity);

	/**
	 * 根据主键查询菜单
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_shift WHERE id = #{id}")
	TbShiftEntity selectByPrimaryKey(Long id);

	/**
	 * 根据主键更新
	 * 
	 * @param id
	 * @return
	 */
	@Update("UPDATE tb_shift SET end = #{end} WHERE id = #{id}")
	int updateByPrimaryKey(TbShiftEntity entity);

	/**
	 * 根据主键删除菜单
	 * 
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_shift WHERE id = #{id}")
	int deleteByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * 
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_shift LIMIT #{index}, #{count}")
	List<TbShiftEntity> selectByPage(int index, int count);

	/**
	 * 查询菜单总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_shift")
	int selectCount();

	/**
	 * 模糊查询分页
	 * 
	 * @param searchTxt
	 * @param index
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_shift WHERE name like '%${searchTxt}%' LIMIT #{index}, #{count}")
	List<TbShiftEntity> selectBySearchAndPage(String searchTxt, int index, int count);

	/**
	 * 模糊查询菜单总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_shift WHERE name like '%${searchTxt}%'")
	int selectCountBySearch(@Param("searchTxt") String searchTxt);
}
