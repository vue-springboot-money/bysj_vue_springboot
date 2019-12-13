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
	@Insert("INSERT INTO tb_session(starring1, starring2, assistant1, assistant2, assistant3, assistant4, tid, `date`, img) VALUES(#{starring1}, #{starring2}, #{assistant1}, #{assistant2}, #{assistant3}, #{assistant4}, #{tid}, #{date}, #{img});")
	int insert(TbSessionEntity entity);

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 */
	@Update("UPDATE tb_session SET starring1=#{starring1}, starring2=#{starring2}, assistant1=#{assistant1}, assistant2=#{assistant2}, assistant3=#{assistant3}, assistant4=#{assistant4}, tid=#{tid}, `date`=#{date}, img=#{img} WHERE id=#{id};")
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

	/**
	 * 获取当前日期之后的所有专场
	 * @param date
	 * @return
	 */
	@Select("SELECT * FROM tb_session WHERE date >= #{date};")
	List<TbSessionEntity> selectByDate(String date);
}
