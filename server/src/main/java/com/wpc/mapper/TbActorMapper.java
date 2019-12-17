package com.wpc.mapper;

import com.wpc.entity.TbActorEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TbActorMapper {

	/**
	 * 插入
	 * 
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_actor(name, img, information, masterpiece, tid) VALUES(#{name}, #{img}, #{information}, #{masterpiece}, #{tid});")
	int insert(TbActorEntity entity);

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 */
	@Update("UPDATE tb_actor SET name=#{name}, img=#{img}, information=#{information}, masterpiece=#{masterpiece}, tid=#{tid} WHERE id=#{id};")
	int updateByPrimaryKey(TbActorEntity entity);

	/**
	 * 根据主键查询演员
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_actor WHERE id = #{id};")
	TbActorEntity selectByPrimaryKey(Long id);

	/**
	 * 根据主键删除演员
	 * 
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_actor WHERE id = #{id};")
	int deleteByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * 
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_actor LIMIT #{index}, #{count};")
	List<TbActorEntity> selectByPage(int index, int count);

	/**
	 * 查询演员总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_actor;")
	int selectCount();

	/**
	 * 模糊查询分页
	 * 
	 * @param searchTxt
	 * @param index
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_actor WHERE name like '%${searchTxt}%' LIMIT #{index}, #{count};")
	List<TbActorEntity> selectBySearchAndPage(String searchTxt, int index, int count);

	/**
	 * 模糊查询演员总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_actor WHERE name like '%${searchTxt}%';")
	int selectCountBySearch(@Param("searchTxt") String searchTxt);

	/**
	 * 查询所有演员
	 *
	 * @return
	 */
	@Select("SELECT * FROM tb_actor;")
	List<TbActorEntity> selectAll();

	/**
	 * 根据队伍id查询演员
	 *
	 * @param tid
	 * @return
	 */
	@Select("SELECT * FROM tb_actor WHERE tid = #{tid};")
	List<TbActorEntity> selectByTid(Long tid);
}
