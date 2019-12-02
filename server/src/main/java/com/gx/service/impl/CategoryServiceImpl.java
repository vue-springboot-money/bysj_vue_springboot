package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.entity.TbCategoryEntity;
import com.gx.mapper.TbCategoryMapper;
import com.gx.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Value("${default.uploadPath}")
	private String uploadPath;

	@Value("${default.img}")
	private String defaultImg;

	@Autowired
	private TbCategoryMapper tbCategoryMapper;

	@Override
	@Transactional
	public TbCategoryEntity createCategory(TbCategoryEntity entity) {
		// check是否有重名类别
		TbCategoryEntity selectResult = tbCategoryMapper.selectByName(entity.getName());

		// 存在重名类别
		if (selectResult != null) {
			return null;
		} else {
			// 不存在重名类别，初始化数据
			// 设置状态为未上架状态
			entity.setState((byte) 0);

			// 添加类别到数据库
			int insertResult = tbCategoryMapper.insert(entity);

			// 添加成功
			if (insertResult == 1) {
				return tbCategoryMapper.selectByName(entity.getName());
			} else {
				// 添加失败
				return null;
			}
		}
	}

	@Override
	public TbCategoryEntity findCategoryById(Long id) {
		TbCategoryEntity selectResult = tbCategoryMapper.selectByPrimaryKey(id);
		return selectResult;
	}

	@Override
	public List<TbCategoryEntity> selectCategoryListByPage(int pageNum) {
		// 每页展示10条数据
		int count = 10;

		if (pageNum < 1) {
			pageNum = 1;
		}

		List<TbCategoryEntity> CategoryList = tbCategoryMapper.selectByPage((pageNum - 1) * count, count);
		return CategoryList;
	}

	@Override
	public TbCategoryEntity updateCategory(TbCategoryEntity entity) {

		// 更新数据库
		int updateResult = tbCategoryMapper.updateByPrimaryKey(entity);

		// 更新成功，返回更新后的结果
		if (updateResult == 1) {
			return tbCategoryMapper.selectByPrimaryKey(entity.getId());
		}
		return null;
	}

	@Override
	public int deleteCategory(Long id) {
		int deleteResult = tbCategoryMapper.deleteByPrimaryKey(id);
		return deleteResult;
	}

	@Override
	public int selectSum() {
		int selectResult = tbCategoryMapper.selectSum();
		return selectResult;
	}
	
	@Override
	public List<TbCategoryEntity> searchCategoryByPage(String searchTxt, int pageNum) {
		int count = 10;
		return tbCategoryMapper.selectBySearchAndPage(searchTxt, (pageNum - 1) * count, count);
	}

	@Override
	public int selectSearchCategoryTotal(String searchTxt) {
		return tbCategoryMapper.selectCountBySearch(searchTxt);
	}

	@Override
	public List<TbCategoryEntity> selectAll() {
		return tbCategoryMapper.selectAll();
	}

}
