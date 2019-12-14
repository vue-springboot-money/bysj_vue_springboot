package com.wpc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wpc.entity.TbOrderEntity;

@Mapper
public interface TbOrderMapper {
	/**
	 * 插入
	 * 
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_order(uid, tid, state) VALUES(#{uid}, #{tid}, #{state});")
	int insert(TbOrderEntity entity);

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 */
	@Update("UPDATE tb_order SET uid=#{uid}, tid=#{tid}, state=#{state} WHERE id=#{id};")
	int updateByPrimaryKey(TbOrderEntity entity);

	/**
	 * 根据主键查询订单
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_order WHERE id = #{id};")
	TbOrderEntity selectByPrimaryKey(Long id);

	/**
	 * 根据主键删除订单
	 * 
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_order WHERE id = #{id};")
	int deleteByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * 
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_order LIMIT #{index}, #{count};")
	List<TbOrderEntity> selectByPage(int index, int count);

	/**
	 * 查询订单总数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_order;")
	int selectCount();

	@Select("SELECT * FROM tb_order WHERE uid = #{uid};")
	List<TbOrderEntity> selectByUid(Long uid);

	@Select("SELECT * FROM tb_order")
	List<TbOrderEntity> selectAll();
}
