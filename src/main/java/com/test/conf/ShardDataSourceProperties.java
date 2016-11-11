package com.test.conf;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix="sharding.jdbc")
public class ShardDataSourceProperties {
	
	private String driverClassName;
	
	private String url;
	
	private String username;
	
	private String password;
	
	private String url0;
	
	private String username0;
	
	private String password0;
	
	private String url1;
	
	private String username1;
	
	private String password1;
	
	private String filters;
	
	private int maxActive;
	
	private int initialSize;
	
	private int maxWait;
	
	private int minIdle;
	
	private int timeBetweenEvictionRunsMillis;
	
	private int minEvictableIdleTimeMillis;
	
	private String validationQuery;
	
	private boolean testWhileIdle;
	
	private boolean testOnBorrow;
	
	private boolean testOnReturn;
	
	private boolean poolPreparedStatements;
	
	private int maxPoolPreparedStatementPerConnectionSize;
	
	private boolean removeAbandoned;

	private int removeAbandonedTimeout;
	
	private boolean logAbandoned;
	
	private List<String> connectionInitSqls;

}
