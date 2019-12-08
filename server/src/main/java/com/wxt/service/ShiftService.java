package com.wxt.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wxt.entity.TbShiftEntity;
import com.wxt.mapper.TbShiftMapper;

@Service
public class ShiftService {

	@Autowired
	private TbShiftMapper tbShiftMapper;

	@Value("${default.count}")
	private int count;

	/**
	 * 创建
	 * 
	 * @param entity
	 * @return
	 */
	public int createShift(TbShiftEntity entity) {
		return tbShiftMapper.insert(entity);
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 * @throws ParseException 
	 */
	public int updateShift(TbShiftEntity entity) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 8);
		entity.setEnd(calendar.getTime());
		return tbShiftMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 查询指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	public TbShiftEntity getShiftById(Long id) {
		return tbShiftMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除指定id的数据
	 * 
	 * @param id
	 * @return
	 */
	public int deleteShiftById(Long id) {
		return tbShiftMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<TbShiftEntity> getShiftListByPage(int page) {
		return tbShiftMapper.selectByPage((page - 1) * count, count);
	}

	/**
	 * 查询数据总数
	 * 
	 * @return
	 */
	public int getShiftCount() {
		return tbShiftMapper.selectCount();
	}

}
