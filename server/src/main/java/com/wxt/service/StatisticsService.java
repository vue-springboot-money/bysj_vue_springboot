package com.wxt.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxt.entity.TbOrderEntity;
import com.wxt.mapper.TbOrderMapper;

@Service
public class StatisticsService {
	@Autowired
	private TbOrderMapper orderMapper;

	public List<Float> getYear() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		String start = year + "-01-01 00:00:00";
		String end = year + "-12-31 24:00:00";
		List<TbOrderEntity> selectResult = orderMapper.selectByTime(start, end);

		Map<Integer, Float> tempMap = new HashMap<Integer, Float>();
		for (TbOrderEntity entity : selectResult) {
			calendar.setTime(entity.getCreatetime());
			calendar.add(Calendar.HOUR, -8);
			int month = calendar.get(Calendar.MONTH) + 1;
			if (tempMap.get(month) == null) {
				tempMap.put(month, entity.getPrice());
			} else {
				tempMap.put(month, tempMap.get(month) + entity.getPrice());
			}
		}

		for (int i = 1; i < 13; i++) {
			if (tempMap.get(i) == null) {
				tempMap.put(i, 0f);
			}
		}

		List<Float> resultList = new ArrayList<Float>();
		for (int i = 1; i < 13; i++) {
			resultList.add(tempMap.get(i));
		}

		return resultList;
	}

	public List<Float> getMonth() {
		Date date = new Date();
		String start = "";
		String end = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); // 传人参数
		int year = calendar.get(Calendar.YEAR); // 年
		int march = calendar.get(Calendar.MARCH) + 1; // 月
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		int maxDate = calendar.get(Calendar.DATE); // 该月多少天
		if (march == 12 || march == 11 || march == 10) {
			start = year + "-" + march + "-01"; // 月初
			end = year + "-" + march + "-" + maxDate; // 月末
		} else {
			start = year + "-0" + march + "-01";
			end = year + "-0" + march + "-" + maxDate;
		}

		List<TbOrderEntity> selectResult = orderMapper.selectByTime(start + " 00:00:00", end + " 24:00:00");
		Map<Integer, Float> resultMap = new HashMap<Integer, Float>();
		for (TbOrderEntity entity : selectResult) {
			calendar.setTime(entity.getCreatetime());
			calendar.add(Calendar.HOUR, -8);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			if (resultMap.get(day) == null) {
				resultMap.put(day, entity.getPrice());
			} else {
				resultMap.put(day, resultMap.get(day) + entity.getPrice());
			}
		}

		for (int i = 0; i < maxDate; i++) {
			if (resultMap.get(i + 1) == null) {
				resultMap.put(i + 1, 0f);
			}
		}

		List<Float> resultArr = new ArrayList<Float>();
		for (int i = 0; i < resultMap.keySet().size(); i++) {
			resultArr.add(resultMap.get(i + 1));
		}

		return resultArr;
	}

	public Float getDay() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String start = year + "-" + (month > 9 ? month + "" : "0" + month) + "-" + (day > 9 ? day + "" : "0" + day)
				+ " 00:00:00";
		String end = year + "-" + (month > 9 ? month + "" : "0" + month) + "-" + (day > 9 ? day + "" : "0" + day)
				+ " 24:00:00";

		float price = 0;

		List<TbOrderEntity> selectResult = orderMapper.selectByTime(start, end);
		for (TbOrderEntity entity : selectResult) {
			price += entity.getPrice();
		}
		return price;
	}
}
