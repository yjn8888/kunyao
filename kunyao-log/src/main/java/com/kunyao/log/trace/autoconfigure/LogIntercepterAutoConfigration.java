package com.kunyao.log.trace.autoconfigure;

import com.kunyao.log.trace.intercepter.SpringMVCLogTraceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableConfigurationProperties(TraceProperties.class)
@ConditionalOnClass(DispatcherServlet.class)
public class LogIntercepterAutoConfigration  extends WebMvcConfigurationSupport {

    @Autowired
    private TraceProperties traceProperties;

    public void addInterceptors(InterceptorRegistry registry) {
        if(traceProperties.isGlobal() && traceProperties.isDistributed()){
            registry.addInterceptor(new SpringMVCLogTraceInterceptor()).addPathPatterns("/*");
            super.addInterceptors(registry);
        }
    }
}
