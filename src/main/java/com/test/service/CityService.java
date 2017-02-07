package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.CityMapper;
import com.test.model.City;

@Service
public class CityService {
	
	@Autowired
	private CityMapper cityMapper;
	
	public void createCity(City city) {
		cityMapper.createCity(city);
	}

}
