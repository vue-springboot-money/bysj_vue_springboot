package com.gx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gx.entity.TbOrderEntity;

@Mapper
public interface TbOrderMapper {

	/**
	 * 根据取餐码查找数据
	 * @param code
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_order WHERE code = #{code};")
	int selectByCode(String code);

	/**
	 * 插入
	 * @param orderEntity
	 * @return
	 */
	@Insert("INSERT INTO tb_order (`no`, uid, price, `comment`, state) VALUES (#{no}, #{uid}, #{price}, #{comment}, #{state});")
	int insert(TbOrderEntity orderEntity);

	/**
	 * 根据用户id查询数据，根据创建时间排序
	 * @param uid
	 * @return
	 */
	@Select("SELECT * FROM tb_order WHERE uid = #{uid} ORDER BY createtime")
	List<TbOrderEntity> selectByUid(Long uid);

	/**
	 * 根据订单编号查询数据
	 * @param no
	 * @return
	 */
	@Select("SELECT * FROM tb_order WHERE no = #{no}")
	List<TbOrderEntity> selectByNo(String no);

	/**
	 * 分页查询所有订单
	 * @return
	 */
	@Select("SELECT * FROM tb_order LIMIT #{index}, #{count}")
	List<TbOrderEntity> selectAll(int index, int count);

	@Select("SELECT COUNT(1) FROM tb_order")
	int selectCount();

	@Update("UPDATE tb_order SET `state` = 1 WHERE `code` = #{code}")
	int updateOrderByCode(String code);

	@Select("SELECT * FROM tb_order WHERE `code` = #{code}")
	TbOrderEntity selectOrderByCode(String code);

}
