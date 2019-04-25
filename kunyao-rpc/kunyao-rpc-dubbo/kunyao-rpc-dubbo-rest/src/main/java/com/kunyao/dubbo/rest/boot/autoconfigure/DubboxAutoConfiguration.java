package com.kunyao.dubbo.rest.boot.autoconfigure;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboxAutoConfiguration {
	
	@Bean
    public DubboxProperties dubboxProperties(){
        return new DubboxProperties();
    }
    
	@Bean
    @ConditionalOnMissingBean(name="dubbo")
    public ProtocolConfig dubbo(DubboxProperties dubboxProperties) {
		return dubboxProperties.getProtocol().initDubboProtocol();
    }

    @Bean
    @ConditionalOnMissingBean
    public ApplicationConfig requestApplicationConfig(DubboxProperties dubboxProperties) {
        return dubboxProperties.getApplication();
    }

    @Bean
    @ConditionalOnMissingBean
    public RegistryConfig requestRegistryConfig(DubboxProperties dubboxProperties) {
        return dubboxProperties.getRegistry();
    }

    @Bean
    @ConditionalOnMissingBean(name="rest")
    public ProtocolConfig rest(DubboxProperties dubboxProperties) {
        return dubboxProperties.getProtocol().getRest();
    }

}
