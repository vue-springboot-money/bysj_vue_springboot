package com.ttcanteen.service.impl;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttcanteen.dto.OrderDto;
import com.ttcanteen.entity.TbOrderEntity;
import com.ttcanteen.entity.TbOrderItemEntity;
import com.ttcanteen.mapper.TbMenuMapper;
import com.ttcanteen.mapper.TbOrderItemMapper;
import com.ttcanteen.mapper.TbOrderMapper;
import com.ttcanteen.service.OrderService;
import com.ttcanteen.utils.CodeGeneratorUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private TbMenuMapper tbMenuMapper;

	@Autowired
	private TbOrderMapper tbOrderMapper;

	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;

	@Override
	public int createOrder(OrderDto orderDto) {
		// 用户id
		Long uid = orderDto.getUid();
		// 菜单集合
		Map<Long, Integer> menuMap = orderDto.getMenuMap();

		// 生成订单编号
		String no = UUID.randomUUID().toString().replace("-", "");
		
		float priceSum = 0;

		// 遍历所有菜单
		for (Long mid : menuMap.keySet()) {
			// 获取菜品单价
			float price = tbMenuMapper.selectByPrimaryKey(mid).getPrice();

			// 累加价格
			priceSum += price;

			// 获取购买数量
			int amount = menuMap.get(mid);

			// 创建订单详情对象
			TbOrderItemEntity orderItemEntity = new TbOrderItemEntity();
			orderItemEntity.setNo(no);
			orderItemEntity.setMid(mid);
			orderItemEntity.setAmount(amount);
			orderItemEntity.setPrice(price * amount);

			// 插入数据库
			tbOrderItemMapper.insert(orderItemEntity);
		}
		
		// 生成取餐码
		String code = "";
		while (true) {
			code = CodeGeneratorUtil.generator();
			if (tbOrderMapper.selectByCode(code) == 0) {
				break;
			}
		}

		// 创建订单对象
		TbOrderEntity orderEntity = new TbOrderEntity();
		orderEntity.setComment("");
		orderEntity.setUid(uid);
		orderEntity.setNo(no);
		orderEntity.setPrice(priceSum);
		orderEntity.setState((byte) 0);
		orderEntity.setCode(code);
		
		// 插入结果
		int insertResult = tbOrderMapper.insert(orderEntity);

		return insertResult;
	}

}
