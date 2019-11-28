package com.wpc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wpc.entity.TbTheaterEntity;

@Mapper
public interface TbTheaterMapper {
	/**
	 * 插入
	 * 
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_theater(name, video, information, address, tel) VALUES(#{name}, #{video}, #{information}, #{address}, #{tel});")
	int insert(TbTheaterEntity entity);

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 */
	@Update("UPDATE tb_theater SET name=#{name}, video=#{video}, information=#{information}, address=#{address}, tel=#{tel} WHERE id=#{id};")
	int updateByPrimaryKey(TbTheaterEntity entity);

	/**
	 * 根据主键查询剧场
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_theater WHERE id = #{id};")
	TbTheaterEntity selectByPrimaryKey(Long id);

	/**
	 * 根据主键删除剧场
	 * 
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_theater WHERE id = #{id};")
	int deleteByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * 
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_theater LIMIT #{index}, #{count};")
	List<TbTheaterEntity> selectByPage(int index, int count);

	/**
	 * 查询剧场总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_theater;")
	int selectCount();

	/**
	 * 模糊查询分页
	 * 
	 * @param searchTxt
	 * @param index
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_theater WHERE name like '%${searchTxt}%' LIMIT #{index}, #{count};")
	List<TbTheaterEntity> selectBySearchAndPage(String searchTxt, int index, int count);

	/**
	 * 模糊查询剧场总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_theater WHERE name like '%${searchTxt}%';")
	int selectCountBySearch(@Param("searchTxt") String searchTxt);
}
