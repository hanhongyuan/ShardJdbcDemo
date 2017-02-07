package com.test.conf;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.MasterSlaveDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.test.id.OrderIdGenerator;
import com.test.sharding.ModuloDatabaseShardingAlgorithm;
import com.test.sharding.ModuloTableShardingAlgorithm;

@Configuration
@EnableConfigurationProperties(ShardDataSourceProperties.class)
public class ShardDataSourceConfig {

	@Autowired
	private ShardDataSourceProperties shardDataSourceProperties;

	private DruidDataSource parentDs() throws SQLException {
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName(shardDataSourceProperties.getDriverClassName());
//		ds.setUsername(shardDataSourceProperties.getUsername());
//		ds.setUrl(shardDataSourceProperties.getUrl());
//		ds.setPassword(shardDataSourceProperties.getPassword());
		ds.setFilters(shardDataSourceProperties.getFilters());
		ds.setMaxActive(shardDataSourceProperties.getMaxActive());
		ds.setInitialSize(shardDataSourceProperties.getInitialSize());
		ds.setMaxWait(shardDataSourceProperties.getMaxWait());
		ds.setMinIdle(shardDataSourceProperties.getMinIdle());
		ds.setTimeBetweenEvictionRunsMillis(shardDataSourceProperties.getTimeBetweenEvictionRunsMillis());
		ds.setMinEvictableIdleTimeMillis(shardDataSourceProperties.getMinEvictableIdleTimeMillis());
		ds.setValidationQuery(shardDataSourceProperties.getValidationQuery());
		ds.setTestWhileIdle(shardDataSourceProperties.isTestWhileIdle());
		ds.setTestOnBorrow(shardDataSourceProperties.isTestOnBorrow());
		ds.setTestOnReturn(shardDataSourceProperties.isTestOnReturn());
		ds.setPoolPreparedStatements(shardDataSourceProperties.isPoolPreparedStatements());
		ds.setMaxPoolPreparedStatementPerConnectionSize(
		        shardDataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
		ds.setRemoveAbandoned(shardDataSourceProperties.isRemoveAbandoned());
		ds.setRemoveAbandonedTimeout(shardDataSourceProperties.getRemoveAbandonedTimeout());
		ds.setLogAbandoned(shardDataSourceProperties.isLogAbandoned());
		ds.setConnectionInitSqls(shardDataSourceProperties.getConnectionInitSqls());
		return ds;
	}

	private DataSource mds0() throws SQLException {
		DruidDataSource ds = parentDs();
		ds.setUsername(shardDataSourceProperties.getMasterUsername0());
		ds.setUrl(shardDataSourceProperties.getMasterUrl0());
		ds.setPassword(shardDataSourceProperties.getMasterPassword0());
		return ds;
	}

	private DataSource mds1() throws SQLException {
		DruidDataSource ds = parentDs();
		ds.setUsername(shardDataSourceProperties.getMasterUsername1());
		ds.setUrl(shardDataSourceProperties.getMasterUrl1());
		ds.setPassword(shardDataSourceProperties.getMasterPassword1());
		return ds;
	}
	
	private DataSource sds0() throws SQLException {
		DruidDataSource ds = parentDs();
		ds.setUsername(shardDataSourceProperties.getSlaveUsername0());
		ds.setUrl(shardDataSourceProperties.getSlaveUrl0());
		ds.setPassword(shardDataSourceProperties.getSlavePassword0());
		return ds;
	}
	
	private DataSource sds1() throws SQLException {
		DruidDataSource ds = parentDs();
		ds.setUsername(shardDataSourceProperties.getSlaveUsername1());
		ds.setUrl(shardDataSourceProperties.getSlaveUrl1());
		ds.setPassword(shardDataSourceProperties.getSlavePassword1());
		return ds;
	}

	private DataSourceRule dataSourceRule() throws SQLException {
		DataSource msDs0 = MasterSlaveDataSourceFactory.createDataSource("ms_0", mds0(), sds0());
		DataSource msDs1 = MasterSlaveDataSourceFactory.createDataSource("ms_1", mds1(), sds1());
		Map<String, DataSource> dataSourceMap = new HashMap<>(2);
		dataSourceMap.put("ds_0", msDs0);
		dataSourceMap.put("ds_1", msDs1);
		DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, "ds_0");
		return dataSourceRule;
	}

	private TableRule userTableRule() throws SQLException {
		TableRule userTableRule = TableRule.builder("t_user").autoIncrementColumns("user_id", OrderIdGenerator.class)
				.databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new ModuloDatabaseShardingAlgorithm()))
		        .dataSourceRule(dataSourceRule()).build();
		return userTableRule;
	}
	
	private TableRule orderTableRule() throws SQLException {
		TableRule orderTableRule = TableRule.builder("t_order").autoIncrementColumns("order_id", OrderIdGenerator.class)
		        .actualTables(Arrays.asList("t_order_0", "t_order_1")).dataSourceRule(dataSourceRule())
		        .databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new ModuloDatabaseShardingAlgorithm()))
		        .tableShardingStrategy(new TableShardingStrategy("order_id", new ModuloTableShardingAlgorithm()))
		        .build();
		return orderTableRule;
	}

	private TableRule orderItemTableRule() throws SQLException {
		TableRule orderItemTableRule = TableRule.builder("t_order_item")
		        .autoIncrementColumns("item_id", OrderIdGenerator.class)
		        .actualTables(Arrays.asList("t_order_item_0", "t_order_item_1")).dataSourceRule(dataSourceRule())
		        .databaseShardingStrategy(new DatabaseShardingStrategy("order_id", new ModuloDatabaseShardingAlgorithm()))
		        .tableShardingStrategy(new TableShardingStrategy("item_id", new ModuloTableShardingAlgorithm()))
		        .build();
		return orderItemTableRule;
	}

	private ShardingRule shardingRule() throws SQLException {
		ShardingRule shardingRule = ShardingRule.builder().dataSourceRule(dataSourceRule())
		        .tableRules(Arrays.asList(userTableRule(), orderTableRule(), orderItemTableRule()))
		        .build();
		return shardingRule;
	}

	@Bean
	public DataSource dataSource() throws SQLException {
		return ShardingDataSourceFactory.createDataSource(shardingRule());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws SQLException {
		return new DataSourceTransactionManager(dataSource());
	}
}
