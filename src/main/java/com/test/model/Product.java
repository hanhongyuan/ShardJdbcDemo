package com.test.model;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Product implements Serializable {
	
	public Integer productId;
	
	public String productName;

}
