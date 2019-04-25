package com.kunyao.dubbo.boot.autoconfigure;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboAutoConfiguration {
	
    @Bean
    public DubboProperties dubboProperties(){
        return new DubboProperties();
    }
    
    @Bean
    @ConditionalOnMissingBean
    public ApplicationConfig requestApplicationConfig(DubboProperties dubboProperties) {
        return dubboProperties.getApplication();
    }

    @Bean
    @ConditionalOnMissingBean
    public RegistryConfig requestRegistryConfig(DubboProperties dubboProperties) {
        return dubboProperties.getRegistry();
    }

    @Bean
    @ConditionalOnMissingBean
    public ProtocolConfig dubbo(DubboProperties dubboProperties) {
        return dubboProperties.getProtocol().getDubbo();
    }

}
