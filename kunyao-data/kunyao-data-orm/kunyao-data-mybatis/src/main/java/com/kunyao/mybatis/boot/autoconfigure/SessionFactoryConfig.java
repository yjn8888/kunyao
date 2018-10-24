package com.kunyao.mybatis.boot.autoconfigure;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class SessionFactoryConfig {
	
	@Autowired
	private DataSource dataSource;
	
	/**     * mybatis 配置路径     */    
//    private static String MYBATIS_CONFIG = "mybatis-config.xml";    
    /**     * mybatis mapper resource 路径     */    
    private static String MAPPER_PATH = "classpath:mybatis/**/*Mapper.xml";    

    private String typeAliasPackage = "com.kunyao";
	
	@Bean  
    public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
	    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();        
	    /** 设置mybatis configuration 扫描路径 */                
//	    sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
	    /** 添加mapper 扫描路径 */        
	     PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();        
//	     String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;
	     sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(MAPPER_PATH));
	    /** 设置datasource */        
	     sqlSessionFactoryBean.setDataSource(dataSource); 
	    /** 设置typeAlias 包扫描路径 */     
	     sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);        
	     return sqlSessionFactoryBean;    
     }
	 
}
