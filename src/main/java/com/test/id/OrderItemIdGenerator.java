package com.test.id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;

@Component
public class OrderItemIdGenerator implements IdGenerator {

	@Autowired
	private StringRedisTemplate template;
	
	@Override
	public Number generateId() {
		return template.boundValueOps("orderItem:id:generate").increment(1);
	}

}
