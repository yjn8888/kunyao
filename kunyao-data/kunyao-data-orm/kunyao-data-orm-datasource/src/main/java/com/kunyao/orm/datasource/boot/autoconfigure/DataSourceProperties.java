package com.kunyao.orm.datasource.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.alibaba.druid.pool.DruidDataSource;

@ConfigurationProperties(prefix = "orm")
public class DataSourceProperties {
	
	private DruidDataSource dataSource;
	
	public DataSourceProperties(){
		this.dataSource = new DruidDataSource();
		this.dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		this.dataSource.setUrl("jdbc:mysql://localhost:3306/epik?characterEncoding=utf8&useSSL=true");
		this.dataSource.setPassword("root");
		this.dataSource.setUsername("root");
	}

	public DruidDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DruidDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
