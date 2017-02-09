package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.ProductMapper;
import com.test.model.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	public void createProduct(Product product) {
		productMapper.createProduct(product);
	}

}
