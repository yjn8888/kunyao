package com.kunyao.util;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 
 * 此类描述的是：springContext工具类
 * @author:
 * @since : 2018年10月17日
 */
@Component
public class SpringContextUtil implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;
	
	/**
	 * 根据bean name得到bean
	 * @param name
	 * @return
	 */
	public static Object getBean(String name){
		return applicationContext.getBean(name);
	}
	
	/**
	 * 根据 name 和 class得到bean
	 * @param name
	 * @param beanType
	 * @return
	 */
	public static Object getBean(String name,Class<?> beanType){
		return applicationContext.getBean(name,beanType);
	}
	
	/**
	 * 根据 class得到bean
	 * @param name
	 * @param beanType
	 * @return
	 */
	public static Object getBean(Class<?> beanType){
		return applicationContext.getBean(beanType);
	}
	
	/** 
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true  
	 * @param name 
	 * @return boolean 
	 */  
	public static boolean containsBean(String name) {  
	    return applicationContext.containsBean(name);  
	  }
	  
	/** 
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。 
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）    
	 * @param name 
	 * @return boolean 
	 * @throws NoSuchBeanDefinitionException 
	 */  
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {  
	   return applicationContext.isSingleton(name);  
	}
	
	/** 
	 * @param name 
	 * @return Class 注册对象的类型 
	 * @throws NoSuchBeanDefinitionException 
	 */  
	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {  
	   return applicationContext.getType(name);  
	}
	
	/** 
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名    
	 * @param name 
	 * @return 
	 * @throws NoSuchBeanDefinitionException 
	 */  
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {  
	   return applicationContext.getAliases(name);  
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextUtil.applicationContext = applicationContext;
	}
}
