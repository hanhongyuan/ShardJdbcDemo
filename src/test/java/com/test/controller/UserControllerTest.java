package com.test.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.Application;
import com.test.model.City;
import com.test.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getUserByUserIdTest() throws JsonParseException, JsonMappingException, IOException {
		String body = restTemplate.getForObject("/user/20", String.class);
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(body, User.class);
		assertThat(user.getUserName()).isEqualTo("asqaaa");
	}
	
//	@Test
	public void getUserByUserIdFromMasterTest() throws JsonParseException, JsonMappingException, IOException {
		String body = restTemplate.getForObject("/user/master/4", String.class);
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(body, User.class);
		assertThat(user.getUserName()).isEqualTo("asq");
	}
	
//	@Test
	public void createUserTest() throws JsonProcessingException {
		User user = new User();
		user.setUserName("刘四");
		City city = new City();
		city.setCityId(19);
		user.setCity(city);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<User> request = new HttpEntity<User>(user, headers);
		restTemplate.postForLocation("/user", request);
	}

}
