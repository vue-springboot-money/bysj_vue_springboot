package com.gx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gx.entity.TbUserEntity;

@Mapper
public interface TbUserMapper {

	/**
	 * 根据用户名查询
	 */
	@Select("SELECT * FROM tb_user WHERE username = #{username};")
	List<TbUserEntity> selectByUsername(String username);

	/**
	 * 插入
	 * @param entity
	 * @return
	 */
	@Insert("INSERT INTO tb_user(username, passwrod, nickname, sex, `type`, address, balance) VALUES(#{username}, #{password}, #{nickname}, #{sex}, #{type}, #{address}, #{balance});")
	int insert(TbUserEntity entity);

	/**
	 * 更新指定id的用户的密码
	 * @param entity
	 * @return
	 */
	@Update("UPDATE tb_user SET password = #{password} WHERE id = #{id}")
	int updatePwdById(TbUserEntity entity);

	/**
	 * 根据主键查询用户
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM tb_user WHERE id = #{id}")
	TbUserEntity selectByPrimaryKey(Long id);

	/**
	 * 根据主键更新昵称和性别
	 * @param id
	 * @return
	 */
	@Update("UPDATE tb_user SET nickname = #{nickname}, sex = #{sex}, `type` = #{type} WHERE id = #{id}")
	int updateByPrimaryKey(TbUserEntity entity);

	/**
	 * 根据主键增加账户余额
	 * @param entity
	 * @return
	 */
	@Update("UPDATE tb_user SET balance = #{balance} WHERE id = #{id}")
	int addBalanceById(TbUserEntity entity);
	
	/**
	 * 根据主键减少账户余额
	 * @param entity
	 * @return
	 */
	@Update("UPDATE tb_user SET balance = balance - #{balance} WHERE id = #{id}")
	int reduceBalanceById(TbUserEntity entity);

	/**
	 * 根据主键删除用户
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_user WHERE id = #{id}")
	int deleteByPrimaryKey(Long id);

	/**
	 * 分页查询
	 * @param i
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_user LIMIT #{index}, #{count}")
	List<TbUserEntity> selectByPage(int index, int count);

	/**
	 * 查询用户总数
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_user")
	int selectCount();

	/**
	 * 模糊查询分页
	 * @param searchTxt
	 * @param index
	 * @param count
	 * @return
	 */
	@Select("SELECT * FROM tb_user WHERE username like '%${searchTxt}%' OR nickname like '%${searchTxt}%' LIMIT #{index}, #{count}")
	List<TbUserEntity> selectBySearchAndPage(String searchTxt, int index, int count);

	/**
	 * 模糊查询用户总数
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM tb_user WHERE username like '%${searchTxt}%' OR nickname like '%${searchTxt}%'")
	int selectCountBySearch(@Param("searchTxt") String searchTxt);

	/**
	 * 根据类别查询
	 * @param b
	 * @return
	 */
	@Select("SELECT * FROM tb_user WHERE `type` = #{type}")
	List<TbUserEntity> selectByType(byte type);
}
