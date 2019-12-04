package com.wxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wxt.entity.TbDeskEntity;

@Mapper
public interface TbDeskMapper {

	/**
	 * 插入
	 * 
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_desk(name, state) VALUES(#{name}, #{state});")
	int insert(TbDeskEntity entity);

	/**
	 * 根据主键更新
	 * 
	 * @param id
	 * @return
	 */
	@Update("UPDATE tb_desk SET name = #{name}, state = #{state} WHERE id = #{id}")
	int updateByPrimaryKey(TbDeskEntity entity);

	/**
	 * 根据主键查询分类
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_desk WHERE id = #{id}")
	TbDeskEntity selectByPrimaryKey(Long id);

	/**
	 * 根据主键删除分类
	 * 
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_desk WHERE id = #{id}")
	int deleteByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * 
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_desk LIMIT #{index}, #{count}")
	List<TbDeskEntity> selectByPage(int index, int count);

	/**
	 * 查询分类总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_desk")
	int selectCount();

	/**
	 * 模糊查询分页
	 * 
	 * @param searchTxt
	 * @param index
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_desk WHERE name like '%${searchTxt}%' LIMIT #{index}, #{count}")
	List<TbDeskEntity> selectBySearchAndPage(String searchTxt, int index, int count);

	/**
	 * 模糊查询分类总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_desk WHERE name like '%${searchTxt}%'")
	int selectCountBySearch(@Param("searchTxt") String searchTxt);

	// 查询所有数据
	@Select("SELECT * FROM tb_desk")
	List<TbDeskEntity> selectAll();

	/**
	 * 查询指定状态的台号
	 * @param state
	 * @return
	 */
	@Select("SELECT * FROM tb_desk WHERE `state` = #{state}")
	List<TbDeskEntity> selectByState(byte state);
}
