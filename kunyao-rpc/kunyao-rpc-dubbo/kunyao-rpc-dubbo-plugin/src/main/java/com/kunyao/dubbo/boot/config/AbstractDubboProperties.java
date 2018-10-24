package com.kunyao.dubbo.boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ModuleConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.kunyao.dubbo.boot.contant.DubboContant;

public abstract class AbstractDubboProperties {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * dubbo注解需要扫描的包
	 */
	private AnnotationBean annotation;

    @NestedConfigurationProperty
    private ApplicationConfig application;

    @NestedConfigurationProperty
    private RegistryConfig registry;

    @NestedConfigurationProperty
	private ProtocolProperties protocol;
    
    /**
	 * 提供方的缺省值，当ProtocolConfig和ServiceConfig某属性没有配置时，采用此缺省值，可选。
	 */
	protected ProviderConfig provider;
	/**
	 * 模块配置，用于配置当前模块信息，可选。
	 */
	protected ModuleConfig module;
	/**
	 * 监控中心配置，用于配置连接监控中心相关信息，可选。
	 */
	protected MonitorConfig monitor;
    
    public AbstractDubboProperties() {
    	logger.info("-----初始化dubbo默认配置信息------");
	}
    
    /**
     * 初始化缺省应用配置
     */
    protected void initDefaultApplication(){
    	application = new ApplicationConfig(DubboContant.DEFAULT_APPLICATION_PREFIX + System.currentTimeMillis());
    }
    
    /**
     * 初始化缺省服务模块配置
     */
    protected void initDefaultProvider(){
    	provider = new ProviderConfig();
    }
    
    /**
     * 初始化缺省服务模块配置
     */
    protected void initDefaultModule(){
    	module = new ModuleConfig();
    }
    
    /**
     * 初始化缺省服务监控中心配置
     */
    protected void initDefaultMonitor(){
    	monitor = new MonitorConfig();
    }
    
    /**
     * 初始化缺省服务注册中心配置
     */
    protected void initDefaultRegistry(){
    	this.registry = new RegistryConfig(DubboContant.DEFAULT_REGISTRY_ADDRESS);
		this.registry.setDefault(true);
    }
    
    /**
     * 初始化dubbo缺省协议配置
     */
    protected void initDefaultProtocol(){
    	this.protocol = new ProtocolProperties();
    	this.protocol.initDubboProtocol().setDefault(true);
    }

    public ApplicationConfig getApplication() {
        return application;
    }

    public void setApplication(ApplicationConfig application) {
        this.application = application;
    }

	public AnnotationBean getAnnotation() {
		return annotation;
	}

	public void setAnnotation(AnnotationBean annotation) {
		this.annotation = annotation;
	}

	public RegistryConfig getRegistry() {
		return registry;
	}

	public void setRegistry(RegistryConfig registry) {
		this.registry = registry;
	}

	public ProtocolProperties getProtocol() {
		return protocol;
	}

	public void setProtocol(ProtocolProperties protocol) {
		this.protocol = protocol;
	}


}
