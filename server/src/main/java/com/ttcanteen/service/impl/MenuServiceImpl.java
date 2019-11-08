package com.ttcanteen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttcanteen.entity.TbMenuEntity;
import com.ttcanteen.mapper.TbMenuMapper;
import com.ttcanteen.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	@Value("${default.uploadPath}")
	private String uploadPath;

	@Value("${default.img}")
	private String defaultImg;

	@Autowired
	private TbMenuMapper tbMenuMapper;

	@Override
	@Transactional
	public TbMenuEntity createMenu(TbMenuEntity entity) {
		// check是否有重名菜单
		TbMenuEntity selectResult = tbMenuMapper.selectByName(entity.getName());

		// 存在重名菜单
		if (selectResult != null) {
			return null;
		} else {
			// 不存在重名菜单，初始化数据
			// 设置状态为未上架状态
			entity.setState((byte) 0);
			// 如果未上传图片，赋予默认图片
			if (entity.getImg() == null) {
				entity.setImg(defaultImg);
			}

			// 添加菜单到数据库
			int insertResult = tbMenuMapper.insert(entity);

			// 添加成功
			if (insertResult == 1) {
				return tbMenuMapper.selectByName(entity.getName());
			} else {
				// 添加失败
				return null;
			}
		}
	}

	@Override
	public TbMenuEntity findMenuById(Long id) {
		TbMenuEntity selectResult = tbMenuMapper.selectByPrimaryKey(id);
		return selectResult;
	}

	@Override
	public List<TbMenuEntity> selectMenuListByPage(int pageNum) {
		// 每页展示10条数据
		int count = 10;

		if (pageNum < 1) {
			pageNum = 1;
		}

		List<TbMenuEntity> menuList = tbMenuMapper.selectByPage((pageNum - 1) * count, count);
		return menuList;
	}

	@Override
	public TbMenuEntity updateMenu(TbMenuEntity entity) {

		if (entity.getImg() == null || entity.getImg().equals("")) {
			entity.setImg(defaultImg);
		} 

		// 更新数据库
		int updateResult = tbMenuMapper.updateByPrimaryKey(entity);

		// 更新成功，返回更新后的结果
		if (updateResult == 1) {
			return tbMenuMapper.selectByPrimaryKey(entity.getId());
		}
		return null;
	}

	@Override
	public int deleteMenu(Long id) {
		int deleteResult = tbMenuMapper.deleteByPrimaryKey(id);
		return deleteResult;
	}

	@Override
	public int selectSum() {
		int selectResult = tbMenuMapper.selectSum();
		return selectResult;
	}

}
