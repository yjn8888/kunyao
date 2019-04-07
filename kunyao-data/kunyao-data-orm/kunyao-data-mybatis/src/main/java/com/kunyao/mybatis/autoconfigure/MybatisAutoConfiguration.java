package com.kunyao.mybatis.autoconfigure;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.kunyao.orm.datasource.autoconfigure.DataSourceProperties;

import java.io.IOException;

@EnableTransactionManagement
@EnableConfigurationProperties(DataSourceProperties.class)
public class MybatisAutoConfiguration {
	
	@Autowired
	private DataSourceProperties dataSourceProperties;
	
	@Bean("druidDataSource")
	public DataSource createDataSource() {  
		return dataSourceProperties.getDruidDataSource();
    }

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.kunyao");
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return mapperScannerConfigurer;
	}

	/**     * mybatis 配置路径     */
//    private static String MYBATIS_CONFIG = "mybatis-config.xml";
	/**     * mybatis mapper resource 路径     */
	private static String MAPPER_PATH = "classpath*:mybatis/**/*.xml";

	private String typeAliasPackage = "com.kunyao";

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("druidDataSource") DataSource dataSource) throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		/** 设置mybatis configuration 扫描路径 */
//	    sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
		/** 添加mapper 扫描路径 */
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
//	     String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;
		sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(MAPPER_PATH));
		/** 设置datasource */
		sqlSessionFactoryBean.setDataSource(dataSource);
//		/** 设置typeAlias 实体包扫描路径 */
//		sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
		return sqlSessionFactoryBean;
	}
	

}
