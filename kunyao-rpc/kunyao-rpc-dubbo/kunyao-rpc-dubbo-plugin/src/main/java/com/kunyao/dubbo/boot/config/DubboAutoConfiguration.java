package com.kunyao.dubbo.boot.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;



@EnableConfigurationProperties(DubboProperties.class)
public class DubboAutoConfiguration {
	
	private static Logger logger = LoggerFactory.getLogger(DubboAutoConfiguration.class);

    @Autowired
    private DubboProperties dubboProperties;
    
    @Bean
    @ConditionalOnMissingBean
    public ApplicationConfig requestApplicationConfig() {
        return dubboProperties.getApplication();
    }

    @Bean
    @ConditionalOnMissingBean
    public RegistryConfig requestRegistryConfig() {
        return dubboProperties.getRegistry();
    }

    @Bean
    @ConditionalOnMissingBean
    public ProtocolConfig dubbo() {
        return dubboProperties.getProtocol().getDubbo();
    }

}
