package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.OrderItemMapper;
import com.test.mapper.OrderMapper;
import com.test.model.Order;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	public Order getOrderByOrderId(Integer orderId) {
		Order order = orderMapper.getOrderByOrderId(orderId);
		return order;
	}
	
	public List<Order> getOrderListByUserId(Integer userId) {
		List<Order> orderList = orderMapper.getOrderListByUserId(userId);
		if (orderList != null) {
			for (Order o : orderList) {
				o.setOrderItemList(orderItemMapper.getOrderItemListByOrderId(o.getOrderId()));
			}
		}
		return orderList;
	}
	
	public void createOrder(Order order) {
		orderMapper.createOrder(order);
	}

}
