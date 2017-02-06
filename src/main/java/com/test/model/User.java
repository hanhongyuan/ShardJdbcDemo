package com.test.model;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class User implements Serializable {

	private Integer userId;
	
	private String userName;
	
}
