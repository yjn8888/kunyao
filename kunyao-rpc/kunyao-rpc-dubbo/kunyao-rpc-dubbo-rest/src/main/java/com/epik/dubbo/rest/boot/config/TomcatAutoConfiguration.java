package com.epik.dubbo.rest.boot.config;

import org.springframework.context.annotation.Configuration;

//@EnableConfigurationProperties(DubboxProperties.class)
@Configuration
public class TomcatAutoConfiguration {
//	
//	@Bean
//    public ServletRegistrationBean dubboxDispatcherServletRegistration() {
////        logger.info("dubbo dispatcherServlet servlet-mapping pattern is {} .", this.dubboxServletMapping);
//
//		ServletRegistrationBean registration = new ServletRegistrationBean(new DispatcherServlet(), "/services/*");
//        registration.setName("dispatcher");
//        registration.setLoadOnStartup(1);
//        registration.setEnabled(true);
//        registration.setOrder(1);
//        return registration;
//    }
//	
//	 /**
//     * Register dispatcherServlet programmatically, 和上述声明方式选一个即可
//     *
//     * ps: 包括设置Servlet额外参数
//     *
//     * @return ServletRegistrationBean
//     */
//    @SuppressWarnings("deprecation")
//	@Bean
//    public ServletListenerRegistrationBean<BootstrapListener> dubboxServletListenerRegistration() {
////        logger.info("dubbo dispatcherServlet servlet-mapping pattern is {} .", this.dubboxServletMapping);
//
//    	ServletListenerRegistrationBean<BootstrapListener> listenerRegistrationBean = new ServletListenerRegistrationBean(new BootstrapListener());
//        return listenerRegistrationBean;
//    }
//    
//    @Bean
//    public ServletListenerRegistrationBean<ContextLoaderListener> springServletListenerRegistration() {
////        logger.info("dubbo dispatcherServlet servlet-mapping pattern is {} .", this.dubboxServletMapping);
//
//    	ServletListenerRegistrationBean<ContextLoaderListener> listenerRegistrationBean = new ServletListenerRegistrationBean<ContextLoaderListener>(new ContextLoaderListener());
//        return listenerRegistrationBean;
//    }
//	@Bean
//	public EmbeddedServletContainerFactory servletContainer(){
//		TomcatEmbeddedServletContainerFactory servletContainerfactory =
//				new TomcatEmbeddedServletContainerFactory();
//		servletContainerfactory.setPort(DubboxContant.DEFAULT_REST_PROTOCOL_PORT);
//		return servletContainerfactory;
//	}
}
