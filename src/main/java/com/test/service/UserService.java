package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdang.ddframe.rdb.sharding.api.HintManager;
import com.test.mapper.CityMapper;
import com.test.mapper.UserMapper;
import com.test.model.City;
import com.test.model.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CityMapper cityMapper;

	public User getUserByUserId(Integer userId) {
		User user = userMapper.getUserByUserId(userId);
		City city = cityMapper.getCityByCityId(user.getCity().getCityId());
		user.setCity(city);
		return user;
	}

	public User getUserByUserIdFromMaster(Integer userId) {
		try (HintManager hintManager = HintManager.getInstance();) {
			hintManager.setMasterRouteOnly();
			return userMapper.getUserByUserId(userId);
		} catch (Exception e) {
			throw e;
		}
	}

	public void createUser(User user) {
		userMapper.createUser(user);
	}

}
