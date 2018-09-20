package com.epik.orm.datasource.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.alibaba.druid.pool.DruidDataSource;

@ConfigurationProperties(prefix = "orm")
public class DataSourceProperties {
	
	private DruidDataSource dataSource;
	
	public DataSourceProperties(){
		dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/epik?characterEncoding=utf8&useSSL=true");
		dataSource.setPassword("root");
		dataSource.setUsername("root");
	}

	public DruidDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DruidDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
