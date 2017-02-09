package com.test.model;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class OrderItem implements Serializable {
	
	private Integer itemId;
	
	private Integer orderId;
	
	private Integer itemNum;
	
	private Integer itemPrice;
	
	private Product product;

}
