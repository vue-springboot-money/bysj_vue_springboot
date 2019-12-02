package com.gx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gx.entity.TbCategoryEntity;

@Mapper
public interface TbCategoryMapper {

	/**
	 * 根据名精确查询
	 * @param name
	 * @return
	 */
	@Select("SELECT * FROM tb_category WHERE name = #{name};")
	TbCategoryEntity selectByName(String name);

	/**
	 * 插入
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_category(name, state) VALUES(#{name}, #{state});")
	int insert(TbCategoryEntity entity);

	/**
	 * 根据id精确查询
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_category WHERE id = #{id};")
	TbCategoryEntity selectByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * @param index
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_category LIMIT #{index}, #{count};")
	List<TbCategoryEntity> selectByPage(int index, int count);

	/**
	 * 查询数据总数
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_category")
	int selectSum();

	/**
	 * 根据主键更新数据
	 * @param entity
	 * @return
	 */
	@Update("UPDATE tb_category SET name=#{name}, state=#{state} WHERE id=#{id};")
	int updateByPrimaryKey(TbCategoryEntity entity);

	/**
	 * 根据主键删除数据
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_category WHERE id=#{id};")
	int deleteByPrimaryKey(Long id);
	
	/**
	 * 模糊查询分页
	 * @param searchTxt
	 * @param index
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_category WHERE name like '%${searchTxt}%' LIMIT #{index}, #{count}")
	List<TbCategoryEntity> selectBySearchAndPage(String searchTxt, int index, int count);

	/**
	 * 模糊查询用户总数
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_category WHERE name like '%${searchTxt}%'")
	int selectCountBySearch(@Param("searchTxt") String searchTxt);

}
