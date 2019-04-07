package com.kunyao.orm.datasource.autoconfigure;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.alibaba.druid.pool.DruidDataSource;

@Data
@ConfigurationProperties(prefix = "spring")
@ConditionalOnClass(DruidDataSource.class)
public class DataSourceProperties {
	
	private DruidDataSource druidDataSource;
	
	public DataSourceProperties(){
		this.druidDataSource = new DruidDataSource();
		this.druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		this.druidDataSource.setUrl("jdbc:mysql://localhost:3306/kunyao?characterEncoding=utf8&useSSL=true");
		this.druidDataSource.setPassword("root");
		this.druidDataSource.setUsername("root");
		this.druidDataSource.setValidationQuery("SELECT 'x'");
		this.druidDataSource.setTestWhileIdle(true);//申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
		this.druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
	}
}
