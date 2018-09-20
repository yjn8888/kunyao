package com.epik.mybatis.boot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.epik.orm.datasource.boot.config.DataSourceProperties;

@EnableTransactionManagement
@EnableConfigurationProperties(DataSourceProperties.class)
@Import({SessionFactoryConfig.class,MapperScannerConfig.class})
public class MybatisAutoConfig {
	
	@Autowired
	private DataSourceProperties dataSourceProperties;
	
	@Bean
	public DataSource createDataSource() {  
		return dataSourceProperties.getDataSource();
    }   
	

}
