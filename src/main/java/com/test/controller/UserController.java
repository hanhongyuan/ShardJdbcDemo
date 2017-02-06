package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.id.UserIdGenerator;
import com.test.model.User;
import com.test.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserIdGenerator userIdGenerator;
	
	@RequestMapping(path="/{userId}", method={RequestMethod.GET})
	public User getUserByUserId(@PathVariable("userId") Integer userId) {
		return userService.getUserByUserId(userId);
	}
	
	@RequestMapping(path="/{userName}", method={RequestMethod.POST})
	public String createUser(@PathVariable("userName") String userName) {
		User user = new User();
		user.setUserId(userIdGenerator.generateId().intValue());
		user.setUserName(userName);
		userService.createUser(user);
		return "success";
	}

}
