package com.test.mapper;

import com.test.model.User;

public interface UserMapper {
	
	User getUserByUserId(Integer userId);
	
	void createUser(User user);

}
