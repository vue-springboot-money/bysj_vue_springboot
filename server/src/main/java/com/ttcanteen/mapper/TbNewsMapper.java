package com.ttcanteen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ttcanteen.entity.TbNewsEntity;

@Mapper
public interface TbNewsMapper {
	
	@Insert("INSERT INTO tb_news(title, img, content, author, state) VALUES(#{title}, #{img}, #{content}, #{author}, #{state});")
	int insert(TbNewsEntity entity);

	@Select("SELECT * FROM tb_news WHERE title = #{title} AND author = #{author}")
	List<TbNewsEntity> selectByTitleAndAuthor(TbNewsEntity entity);

	@Delete("DELETE FROM tb_news WHERE id = #{id}")
	int deleteByPrimaryKey(Long id);

	@Update("UPDATE tb_news SET title = #{title}, img = #{img}, content = #{content}, author = #{author}, state = #{state} WHERE id = #{id}")
	int updateByPrimaryKey(TbNewsEntity entity);

	@Select("SELECT * FROM tb_news WHERE id = #{id}")
	TbNewsEntity selectByPrimaryKey(Long id);

	@Select("SELECT * FROM tb_news LIMIT #{index}, #{count}")
	List<TbNewsEntity> selectByPage(int index, int count);

	@Select("SELECT COUNT(1) FROM tb_news")
	int selectCount();

	
}
