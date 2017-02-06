package com.test.mapper;

import java.util.List;

import com.test.model.OrderItem;

public interface OrderItemMapper {
	
	List<OrderItem> getOrderItemListByOrderId(Integer orderId);
	
	void createOrderItem(OrderItem orderItem);

}
