package com.test.model;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class City implements Serializable {
	
	public Integer cityId;
	
	public String cityName;

}
