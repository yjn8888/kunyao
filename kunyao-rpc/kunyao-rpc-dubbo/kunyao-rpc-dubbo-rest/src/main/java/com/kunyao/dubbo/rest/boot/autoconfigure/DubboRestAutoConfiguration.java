package com.kunyao.dubbo.rest.boot.autoconfigure;

import com.kunyao.dubbo.boot.contant.DubboContant;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboRestAutoConfiguration {
	
    @Bean
    @ConditionalOnMissingBean(name="rest")
    public ProtocolConfig rest(@Qualifier("dubbo") ProtocolConfig dubbo) {
        dubbo.setDefault(false);
	    ProtocolConfig rest = new ProtocolConfig(DubboContant.DEFAULT_REST_PROTOCOL,DubboContant.DEFAULT_REMOTING_PORT);
        rest.setId(DubboContant.DEFAULT_REST_PROTOCOL);
        rest.setKeepAlive(false);
        rest.setServer(DubboContant.DEFAULT_REST_PROTOCOL_SERVER);
        rest.setThreads(DubboContant.DEFAULT_REST_PROTOCOL_THREADS);
        rest.setAccepts(DubboContant.DEFAULT_REST_PROTOCOL_ACCEPTS);
        rest.setExtension(DubboContant.DEFAULT_REST_PROTOCOL_EXTENSION);
        rest.setPort(DubboContant.DEFAULT_REST_PROTOCOL_PORT);
        rest.setDefault(true);
        return rest;
    }

}
