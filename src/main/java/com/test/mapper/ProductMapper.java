package com.test.mapper;

import com.test.model.City;
import com.test.model.Product;

public interface ProductMapper {
	
	public void createProduct(Product product);
	
	public City getProductByProductId(Integer productId);

}
