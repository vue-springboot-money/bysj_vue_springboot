package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.entity.TbGoodEntity;
import com.gx.mapper.TbGoodMapper;
import com.gx.service.GoodService;

@Service
public class GoodServiceImpl implements GoodService {
	@Value("${default.uploadPath}")
	private String uploadPath;

	@Value("${default.img}")
	private String defaultImg;

	@Autowired
	private TbGoodMapper tbGoodMapper;

	@Override
	@Transactional
	public TbGoodEntity createGood(TbGoodEntity entity) {
		// check是否有重名商品
		TbGoodEntity selectResult = tbGoodMapper.selectByName(entity.getName());

		// 存在重名商品
		if (selectResult != null) {
			return null;
		} else {
			// 不存在重名商品，初始化数据
			// 设置状态为未上架状态
			entity.setState((byte) 0);
			// 如果未上传图片，赋予默认图片
			if (entity.getImg() == null) {
				entity.setImg(defaultImg);
			}

			// 添加商品到数据库
			int insertResult = tbGoodMapper.insert(entity);

			// 添加成功
			if (insertResult == 1) {
				return tbGoodMapper.selectByName(entity.getName());
			} else {
				// 添加失败
				return null;
			}
		}
	}

	@Override
	public TbGoodEntity findGoodById(Long id) {
		TbGoodEntity selectResult = tbGoodMapper.selectByPrimaryKey(id);
		return selectResult;
	}

	@Override
	public List<TbGoodEntity> selectGoodListByPage(int pageNum) {
		// 每页展示10条数据
		int count = 10;

		if (pageNum < 1) {
			pageNum = 1;
		}

		List<TbGoodEntity> GoodList = tbGoodMapper.selectByPage((pageNum - 1) * count, count);
		return GoodList;
	}

	@Override
	public int updateGood(TbGoodEntity entity) {

		// 更新数据库
		int updateResult = tbGoodMapper.updateByPrimaryKey(entity);

		return updateResult;
	}

	@Override
	public int deleteGood(Long id) {
		int deleteResult = tbGoodMapper.deleteByPrimaryKey(id);
		return deleteResult;
	}

	@Override
	public int selectSum() {
		int selectResult = tbGoodMapper.selectSum();
		return selectResult;
	}

}
