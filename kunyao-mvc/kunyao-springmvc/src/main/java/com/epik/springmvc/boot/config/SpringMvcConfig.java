package com.epik.springmvc.boot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan({"com.epik"})
@Import({ViewResolverConfig.class})
public class SpringMvcConfig extends WebMvcConfigurerAdapter{
	
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
	
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/index").setViewName("/WEB-INF/views/html/index.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE );
//		super.addViewControllers(registry);
	}
}
