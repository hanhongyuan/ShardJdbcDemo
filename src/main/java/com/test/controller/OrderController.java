package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.id.OrderIdGenerator;
import com.test.model.Order;
import com.test.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderIdGenerator orderIdGenerator;
	
	@RequestMapping(path="/{userId}", method={RequestMethod.GET})
	public List<Order> getOrderListByUserId(@PathVariable("userId") Integer userId) {
		return orderService.getOrderListByUserId(userId);
	}
	
	@RequestMapping(path="/{userId}/{orderAmount}", method={RequestMethod.POST})
	public String createOrder(@PathVariable("userId") Integer userId, @PathVariable("orderAmount") Integer orderAmount) {
		Order order = new Order();
		order.setOrderId(orderIdGenerator.generateId().intValue());
		order.setOrderAmount(orderAmount);
		order.setUserId(userId);
		orderService.createOrder(order);
		return "success";
	}

}
