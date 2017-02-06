package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.OrderItemMapper;
import com.test.model.OrderItem;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	public List<OrderItem> getOrderItemListByOrderId(Integer orderId) {
		return orderItemMapper.getOrderItemListByOrderId(orderId);
	}
	
	public void createOrderItem(OrderItem orderItem) {
		orderItemMapper.createOrderItem(orderItem);
	}

}
