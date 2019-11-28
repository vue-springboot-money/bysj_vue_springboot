package com.wpc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wpc.entity.TbTeamEntity;

@Mapper
public interface TbTeamMapper {
	/**
	 * 插入
	 * 
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_team(name) VALUES(#{name});")
	int insert(TbTeamEntity entity);

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 */
	@Update("UPDATE tb_team SET name=#{name} WHERE id=#{id};")
	int updateByPrimaryKey(TbTeamEntity entity);

	/**
	 * 根据主键查询队伍
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_team WHERE id = #{id};")
	TbTeamEntity selectByPrimaryKey(Long id);

	/**
	 * 根据主键删除队伍
	 * 
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_team WHERE id = #{id};")
	int deleteByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * 
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_team LIMIT #{index}, #{count};")
	List<TbTeamEntity> selectByPage(int index, int count);

	/**
	 * 查询队伍总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_team;")
	int selectCount();

	/**
	 * 模糊查询分页
	 * 
	 * @param searchTxt
	 * @param index
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_team WHERE name like '%${searchTxt}%' LIMIT #{index}, #{count};")
	List<TbTeamEntity> selectBySearchAndPage(String searchTxt, int index, int count);

	/**
	 * 模糊查询队伍总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_team WHERE name like '%${searchTxt}%';")
	int selectCountBySearch(@Param("searchTxt") String searchTxt);
}
