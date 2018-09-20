package com.epik.dubbo.boot.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.epik.dubbo.config.spring.AnnotationBean;

@EnableConfigurationProperties(DubboProperties.class)
public class DubboAutoConfiguration {
	
	private static Logger logger = Logger.getLogger(DubboAutoConfiguration.class);

    @Autowired
    private DubboProperties dubboProperties;
    
    @Bean
    private static AnnotationBean requestAnnotation(@Value("${dubbo.annotation.package:com.hanhua,com.epik}") String packageName) {
    	AnnotationBean annotationBean = new AnnotationBean();
		if(StringUtils.isNotBlank(packageName)){
			annotationBean.setPackage(packageName);
			logger.info("[DubboAutoConfiguration.annotationBean] " + packageName);
		}
		return annotationBean;
    }

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

//    @Bean
//    @ConfigurationProperties(prefix = "endpoints.dubbo", ignoreUnknownFields = false)
//    public DubboEndpoint dubboEndpoint() {
//        return new DubboEndpoint();
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public MonitorConfig monitorConfig() {
//        MonitorConfig monitorConfig = new MonitorConfig();
//        monitorConfig.setProtocol("registry");
//        return monitorConfig;
//    }
//
//
//    @Bean
//    public DubboHealthIndicator dubboHealthIndicator() {
//        return new DubboHealthIndicator();
//    }
}
