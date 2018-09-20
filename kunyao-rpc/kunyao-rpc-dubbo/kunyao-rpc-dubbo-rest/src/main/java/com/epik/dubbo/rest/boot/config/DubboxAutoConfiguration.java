package com.epik.dubbo.rest.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;

@EnableConfigurationProperties(DubboxProperties.class)
public class DubboxAutoConfiguration {
	
//	private Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
    private DubboxProperties dubboxProperties;
    
	@Bean
    @ConditionalOnMissingBean(name="dubbo")
    public ProtocolConfig dubbo() {
		return dubboxProperties.getProtocol().initDubboProtocol();
    }

    @Bean
    @ConditionalOnMissingBean
    public ApplicationConfig requestApplicationConfig() {
        return dubboxProperties.getApplication();
    }

    @Bean
    @ConditionalOnMissingBean
    public RegistryConfig requestRegistryConfig() {
        return dubboxProperties.getRegistry();
    }

    @Bean
    @ConditionalOnMissingBean(name="rest")
    public ProtocolConfig rest() {
        return dubboxProperties.getProtocol().getRest();
    }
    
//    @Value("${shutdown.latch.domain.name:com.foreveross.lifecycles}")
//    private String shutdownLatchDomainName;
//
//
//    @Bean
//    @ConditionalOnClass(name = "com.alibaba.dubbo.rpc.Exporter")
//    public DubboxServiceLatchCommandLineRunner configureDubboxServiceLatchCommandLineRunner() {
//        logger.debug("DubboxAutoConfiguration enabled by adding DubboxServiceLatchCommandLineRunner.\nShutdownLatchDomainName is " + this.shutdownLatchDomainName);
//
//        DubboxServiceLatchCommandLineRunner runner = new DubboxServiceLatchCommandLineRunner();
//        runner.setDomain(shutdownLatchDomainName);
//        return runner;
//    }
    
//    @Bean
//    @ConditionalOnMissingBean(BootstrapListener.class)
//    public BootstrapListener bootstrapListener() {
//        logger.debug("add com.alibaba.dubbo.remoting.http.servlet.BootstrapListener...");
//        return new BootstrapListener();
//    }
//    
//    /**
//     * 加载context覆盖成com.alibaba.dubbo.remoting.http.servlet.DispatcherServlet
//     *
//     * dubbox rest协议必须有DispatcherServlet容器加载和结束需要设置
//     *
//     *
//     * @return DispatcherServlet
//     */
//    @Bean
//    public DispatcherServlet dispatcherServlet() {
//        logger.debug("add com.alibaba.dubbo.remoting.http.servlet.DispatcherServlet...");
//        return new DispatcherServlet();
//    }
    
    /**
     * Register dispatcherServlet programmatically, 和上述声明方式选一个即可
     *
     * ps: 包括设置Servlet额外参数
     *
     * @return ServletRegistrationBean
     */
//    @SuppressWarnings("deprecation")
//	@Bean
//    public ServletRegistrationBean dubboxDispatcherServletRegistration() {
////        logger.info("dubbo dispatcherServlet servlet-mapping pattern is {} .", this.dubboxServletMapping);
//
//		ServletRegistrationBean registration = new ServletRegistrationBean(new DispatcherServlet(), this.dubboxServletMapping);
//        registration.setName("dubbox-dispatcherServlet");
//        registration.setLoadOnStartup(1);
//        return registration;
//    }

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
