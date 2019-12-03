package com.gx.service.impl;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dto.OrderDto;
import com.gx.dto.OrderItemDto;
import com.gx.entity.TbLogisticsEntity;
import com.gx.entity.TbOrderEntity;
import com.gx.entity.TbOrderItemEntity;
import com.gx.entity.TbUserEntity;
import com.gx.mapper.TbGoodMapper;
import com.gx.mapper.TbLogisticsMapper;
import com.gx.mapper.TbOrderItemMapper;
import com.gx.mapper.TbOrderMapper;
import com.gx.mapper.TbUserMapper;
import com.gx.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private TbUserMapper tbUserMapper;

	@Autowired
	private TbGoodMapper tbGoodMapper;

	@Autowired
	private TbOrderMapper tbOrderMapper;

	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;

	@Autowired
	private TbLogisticsMapper logisticsMapper;

	@Override
	@Transactional
	public int createOrder(OrderDto orderDto) {
		// 用户id
		Long uid = orderDto.getUid();
		// 商品集合
		List<OrderItemDto> itemList = orderDto.getItemList();

		// 生成订单编号
		String no = UUID.randomUUID().toString().replace("-", "");

		float priceSum = 0;

		// 遍历所有商品
		for (OrderItemDto item : itemList) {
			// 转换商品id
			Long gid = item.getId();

			// 获取商品单价
			float price = tbGoodMapper.selectByPrimaryKey(gid).getPrice();

			// 获取购买数量
			int amount = item.getCount();

			// 累加价格
			priceSum += price * amount;

			// 创建订单详情对象
			TbOrderItemEntity orderItemEntity = new TbOrderItemEntity();
			orderItemEntity.setNo(no);
			orderItemEntity.setGid(gid);
			orderItemEntity.setAmount(amount);
			orderItemEntity.setPrice(price * amount);

			// 插入数据库
			tbOrderItemMapper.insert(orderItemEntity);
		}

		// 创建订单对象
		TbOrderEntity orderEntity = new TbOrderEntity();
		orderEntity.setComment("");
		orderEntity.setUid(uid);
		orderEntity.setNo(no);
		orderEntity.setPrice(priceSum);
		orderEntity.setState((byte) 0);

		// 插入结果
		tbOrderMapper.insert(orderEntity);

		// 获取快递员
		List<TbUserEntity> selectResult = tbUserMapper.selectByType((byte) 1);

		Random random = new Random();
		TbUserEntity courier = selectResult.get(random.nextInt(selectResult.size() - 1));
		TbLogisticsEntity entity = new TbLogisticsEntity();
		entity.setOid(tbOrderMapper.selectByNo(no).get(0).getId());
		entity.setUid(courier.getId());
		entity.setState((byte) 0);
		entity.setContent("");
		int insert = logisticsMapper.insert(entity);

		return insert;
	}

	@Override
	public List<TbOrderEntity> getOrderListByUid(Long uid) {
		List<TbOrderEntity> selectResult = tbOrderMapper.selectByUid(uid);
		return selectResult;
	}

	@Override
	public List<TbOrderItemEntity> getOrderItemByNo(String no) {
		List<TbOrderItemEntity> selectResult = tbOrderItemMapper.selectByNo(no);
		return selectResult;
	}

	@Override
	public List<TbOrderEntity> selectOrderListByPage(int pageNum) {
		int count = 10;
		return tbOrderMapper.selectAll((pageNum - 1) * count, count);
	}

	@Override
	public int selectOrderTotal() {
		return tbOrderMapper.selectCount();
	}

	@Override
	public List<TbOrderEntity> getOrderListByUidAndPage(Long uid, int page) {
		int count = 10;
		return tbOrderMapper.selectByUidAndPage(uid, (page - 1) * count, count);
	}

}
