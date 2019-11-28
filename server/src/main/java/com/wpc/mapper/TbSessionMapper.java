package com.wpc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wpc.entity.TbSessionEntity;

@Mapper
public interface TbSessionMapper {
	/**
	 * 插入
	 * 
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_session(starring, assistant, tid, `date`, img) VALUES(#{starring}, #{assistant}, #{tid}, #{date}, #{img});")
	int insert(TbSessionEntity entity);

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 */
	@Update("UPDATE tb_session SET starring=#{starring}, assistant=#{assistant}, tid=#{tid}, `date`=#{date}, img=#{img} WHERE id=#{id};")
	int updateByPrimaryKey(TbSessionEntity entity);

	/**
	 * 根据主键查询专场
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_session WHERE id = #{id};")
	TbSessionEntity selectByPrimaryKey(Long id);

	/**
	 * 根据主键删除专场
	 * 
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_session WHERE id = #{id};")
	int deleteByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * 
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_session LIMIT #{index}, #{count};")
	List<TbSessionEntity> selectByPage(int index, int count);

	/**
	 * 查询专场总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_session;")
	int selectCount();
}
