package com.test.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Order implements Serializable {

	private Integer userId;

	private Integer orderId;

    private Integer orderAmount;
    
    private List<OrderItem> orderItemList;

}
