package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.UserMapper;
import com.test.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public User getUserByUserId(Integer userId) {
		return userMapper.getUserByUserId(userId);
	}
	
	public void createUser(User user) {
		userMapper.createUser(user);
	}

}
