package com.gx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gx.entity.TbGoodEntity;

@Mapper
public interface TbGoodMapper {

	/**
	 * 根据菜单名精确查询
	 * @param name
	 * @return
	 */
	@Select("SELECT * FROM tb_good WHERE name = #{name};")
	TbGoodEntity selectByName(String name);

	/**
	 * 插入
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_good(name, img, price, state, cid) VALUES(#{name}, #{img}, #{price}, #{state}, #{cid});")
	int insert(TbGoodEntity entity);

	/**
	 * 根据id精确查询
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_good WHERE id = #{id};")
	TbGoodEntity selectByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * @param index
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_good LIMIT #{index}, #{count};")
	List<TbGoodEntity> selectByPage(int index, int count);

	/**
	 * 查询数据总数
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_good")
	int selectSum();

	/**
	 * 根据主键更新数据
	 * @param entity
	 * @return
	 */
	@Update("UPDATE tb_good SET name=#{name}, img=#{img}, price=#{price}, state=#{state}, cid=#{cid} WHERE id=#{id};")
	int updateByPrimaryKey(TbGoodEntity entity);

	/**
	 * 根据主键删除数据
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_good WHERE id=#{id};")
	int deleteByPrimaryKey(Long id);

	/**
	 * 根据分类分页查询
	 * @param cid
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_good WHERE cid = #{cid} LIMIT #{index}, #{count}")
	List<TbGoodEntity> selectByCidAndPage(Long cid, int index, int count);

}
