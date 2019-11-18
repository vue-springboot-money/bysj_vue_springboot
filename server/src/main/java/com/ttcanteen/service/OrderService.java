package com.ttcanteen.service;

import java.util.List;

import com.ttcanteen.dto.OrderDto;
import com.ttcanteen.entity.TbOrderEntity;
import com.ttcanteen.entity.TbOrderItemEntity;

public interface OrderService {

	/**
	 * 创建订单
	 * @return
	 */
	public int createOrder(OrderDto orderDto);

	/**
	 * 获取指定用户id的所有订单
	 * @param uid
	 * @return
	 */
	public List<TbOrderEntity> getOrderListByUid(Long uid);

	/**
	 * 根据订单编号查询订单
	 * @param no
	 * @return
	 */
	public List<TbOrderItemEntity> getOrderItemByNo(String no);

	/**
	 * 分页查询订单，按创建时间正序排列
	 * @param pageNum
	 * @return
	 */
	public List<TbOrderEntity> selectOrderListByPage(int pageNum);

	/**
	 * 查询订单总数
	 * @return
	 */
	public int selectOrderTotal();

	/**
	 * 取餐
	 * @param code
	 * @return
	 */
	public List<TbOrderItemEntity> takeMeal(String code);
}
