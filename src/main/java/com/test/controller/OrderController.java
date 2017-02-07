package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.id.OrderIdGenerator;
import com.test.id.OrderItemIdGenerator;
import com.test.model.Order;
import com.test.model.OrderItem;
import com.test.service.OrderItemService;
import com.test.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrderIdGenerator orderIdGenerator;
	
	@Autowired
	private OrderItemIdGenerator orderItemIdGenerator;
	
	@RequestMapping(path="/{userId}", method={RequestMethod.GET})
	public List<Order> getOrderListByUserId(@PathVariable("userId") Integer userId) {
		return orderService.getOrderListByUserId(userId);
	}
	
	@RequestMapping(path="/oid/{orderId}", method={RequestMethod.GET})
	public Order getOrderByOrderId(@PathVariable("orderId") Integer orderId) {
		return orderService.getOrderByOrderId(orderId);
	}
	
	@RequestMapping(method={RequestMethod.POST})
	public String createOrder(@RequestBody Order order) {
		int orderId = orderIdGenerator.generateId().intValue();
		order.setOrderId(orderId);
		orderService.createOrder(order);
		for (OrderItem oi : order.getOrderItemList()) {
			oi.setItemId(orderItemIdGenerator.generateId().intValue());
			oi.setOrderId(orderId);
			orderItemService.createOrderItem(oi);
		}
		
		return "success";
	}

}
