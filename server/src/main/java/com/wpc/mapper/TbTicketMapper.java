package com.wpc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wpc.entity.TbTicketEntity;

@Mapper
public interface TbTicketMapper {
	/**
	 * 插入
	 * 
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_ticket(pid, sid, price, num, version, state) VALUES(#{pid}, #{sid}, #{price}, #{num}, #{version}, #{state});")
	int insert(TbTicketEntity entity);

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 */
	@Update("UPDATE tb_ticket SET pid=#{pid}, sid=#{sid}, price=#{price}, num=#{num}, version=version+1, state=#{state} WHERE id=#{id} AND version = #{version};")
	int updateByPrimaryKey(TbTicketEntity entity);

	/**
	 * 根据主键查询门票
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_ticket WHERE id = #{id};")
	TbTicketEntity selectByPrimaryKey(Long id);

	/**
	 * 根据主键删除门票
	 * 
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_ticket WHERE id = #{id};")
	int deleteByPrimaryKey(Long id);

	/**
	 * 分页查询非专场
	 * 
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_ticket WHERE pid <> NULL LIMIT #{index}, #{count};")
	List<TbTicketEntity> selectProgramTicketByPage(int index, int count);

	/**
	 * 查询非专场门票总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_ticket WHERE pid <> NULL;")
	int selectProgramTicketCount();

	/**
	 * 分页查询非专场
	 * 
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_ticket WHERE sid <> NULL LIMIT #{index}, #{count};")
	List<TbTicketEntity> selectSessionTicketByPage(int index, int count);

	/**
	 * 查询非专场门票总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_ticket WHERE sid <> NULL;")
	int selectSessionTicketCount();
}
