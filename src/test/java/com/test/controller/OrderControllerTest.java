package com.test.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.test.Application;
import com.test.model.Order;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getOrderListByUserIdTest() throws JsonParseException, JsonMappingException, IOException {
		String body = restTemplate.getForObject("/order/1", String.class);
		ObjectMapper mapper = new ObjectMapper();
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Order.class);
		List<Order> orderList = mapper.readValue(body, listType);
		assertThat(orderList.size()).isEqualTo(2);
	}
	
//	@Test
	public void getOrderByOrderIdTest() throws JsonParseException, JsonMappingException, IOException {
		String body = restTemplate.getForObject("/order/oid/4", String.class);
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.readValue(body, Order.class);
		assertThat(order.getOrderAmount()).isEqualTo(2011);
	}

//	@Test
	public void createOrderTest() {
		String json = "{\"userId\":1,\"orderAmount\":1001,\"orderItemList\":[{\"itemNum\":2,\"itemPrice\":30,\"product\":{\"productId\":4}}]}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(json, headers);
		restTemplate.postForLocation("/order", request);
	}

}
