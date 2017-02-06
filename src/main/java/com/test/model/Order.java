package com.test.model;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Order implements Serializable {

	private int userId;

	private int orderId;

    private int orderAmount;

}
