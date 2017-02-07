package com.test.conf;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix="sharding.jdbc")
public class ShardDataSourceProperties {
	
	private String driverClassName;
	
	private String masterUrl0;
	
	private String masterUsername0;
	
	private String masterPassword0;
	
	private String masterUrl1;
	
	private String masterUsername1;
	
	private String masterPassword1;
	
	private String slaveUrl0;
	
	private String slaveUsername0;
	
	private String slavePassword0;
	
	private String slaveUrl1;
	
	private String slaveUsername1;
	
	private String slavePassword1;
	
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
