package com.ttcanteen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ttcanteen.entity.TbNewsEntity;

@Mapper
public interface TbNewsMapper {
	
	@Insert("INSERT INTO tb_news(title, img, author, state) VALUES(#{title}, #{img}, #{author}, #{state});")
	int insert(TbNewsEntity entity);

	@Select("SELECT * FROM tb_news WHERE title = #{title} AND author = #{author}")
	List<TbNewsEntity> selectByTitleAndAuthor(TbNewsEntity entity);

	
}
