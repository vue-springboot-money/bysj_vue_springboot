package com.wpc.mapper;

import com.wpc.entity.TbProgramEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TbProgramMapper {
	/**
	 * 插入
	 * 
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_program(tid, content, `date`) VALUES(#{tid}, #{content}, #{date});")
	int insert(TbProgramEntity entity);

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 */
	@Update("UPDATE tb_program SET tid=#{tid}, content=#{content}, `date`=#{date} WHERE id=#{id};")
	int updateByPrimaryKey(TbProgramEntity entity);

	/**
	 * 根据主键查询节目单
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_program WHERE id = #{id};")
	TbProgramEntity selectByPrimaryKey(Long id);

	/**
	 * 根据主键删除节目单
	 * 
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_program WHERE id = #{id};")
	int deleteByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * 
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_program LIMIT #{index}, #{count};")
	List<TbProgramEntity> selectByPage(int index, int count);

	/**
	 * 查询节目单总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_program;")
	int selectCount();

	/**
	 * 模糊查询分页
	 * 
	 * @param searchTxt
	 * @param index
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_program WHERE content like '%${searchTxt}%' LIMIT #{index}, #{count};")
	List<TbProgramEntity> selectBySearchAndPage(String searchTxt, int index, int count);

	/**
	 * 模糊查询节目单总数
	 * 
	 * @return
	 */
    @Select("SELECT COUNT(1) FROM tb_program WHERE content like '%${searchTxt}%';")
    int selectCountBySearch(@Param("searchTxt") String searchTxt);

    /**
     * 获取当前日期之后的所有节目单
     *
     * @param date
     * @return
     */
    @Select("SELECT * FROM tb_program WHERE date >= #{date};")
    List<TbProgramEntity> selectByDate(String date);

    @Select("SELECT * FROM tb_program WHERE tid = #{tid} AND date >= #{date}")
    List<TbProgramEntity> selectByTid(Long tid, String date);
}
