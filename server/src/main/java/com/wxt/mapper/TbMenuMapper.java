package com.wpc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wpc.entity.TbMenuEntity;

@Mapper
public interface TbMenuMapper {

	/**
	 * 插入
	 * 
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_menu(name, img, price, state, cid) VALUES(#{name}, #{img}, #{price}, #{state}, #{cid});")
	int insert(TbMenuEntity entity);

	/**
	 * 根据主键查询菜单
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_menu WHERE id = #{id}")
	TbMenuEntity selectByPrimaryKey(Long id);

	/**
	 * 根据主键更新
	 * 
	 * @param id
	 * @return
	 */
	@Update("UPDATE tb_menu SET name = #{name}, img = #{img}, price = #{price}, state = #{state}, cid = #{cid} WHERE id = #{id}")
	int updateByPrimaryKey(TbMenuEntity entity);

	/**
	 * 根据主键删除菜单
	 * 
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_menu WHERE id = #{id}")
	int deleteByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * 
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_menu LIMIT #{index}, #{count}")
	List<TbMenuEntity> selectByPage(int index, int count);

	/**
	 * 查询菜单总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_menu")
	int selectCount();

	/**
	 * 模糊查询分页
	 * 
	 * @param searchTxt
	 * @param index
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_menu WHERE name like '%${searchTxt}%' LIMIT #{index}, #{count}")
	List<TbMenuEntity> selectBySearchAndPage(String searchTxt, int index, int count);

	/**
	 * 模糊查询菜单总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_menu WHERE name like '%${searchTxt}%'")
	int selectCountBySearch(@Param("searchTxt") String searchTxt);
}
