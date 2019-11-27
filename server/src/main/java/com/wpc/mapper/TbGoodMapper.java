package com.wpc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wpc.entity.TbGoodEntity;

@Mapper
public interface TbGoodMapper {

	/**
	 * 根据菜单名精确查询
	 * @param name
	 * @return
	 */
	@Select("SELECT * FROM tb_menu WHERE name = #{name};")
	TbGoodEntity selectByName(String name);

	/**
	 * 插入
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_menu(name, img, price, state) VALUES(#{name}, #{img}, #{price}, #{state});")
	int insert(TbGoodEntity entity);

	/**
	 * 根据id精确查询
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_menu WHERE id = #{id};")
	TbGoodEntity selectByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * @param index
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_menu LIMIT #{index}, #{count};")
	List<TbGoodEntity> selectByPage(int index, int count);

	/**
	 * 查询数据总数
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_menu")
	int selectSum();

	/**
	 * 根据主键更新数据
	 * @param entity
	 * @return
	 */
	@Update("UPDATE tb_menu SET name=#{name}, img=#{img}, price=#{price}, state=#{state} WHERE id=#{id};")
	int updateByPrimaryKey(TbGoodEntity entity);

	/**
	 * 根据主键删除数据
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_menu WHERE id=#{id};")
	int deleteByPrimaryKey(Long id);

}
