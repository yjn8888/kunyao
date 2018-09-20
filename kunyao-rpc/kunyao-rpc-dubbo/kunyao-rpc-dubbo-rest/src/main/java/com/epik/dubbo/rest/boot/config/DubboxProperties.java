package com.epik.dubbo.rest.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.epik.dubbo.boot.config.AbstractDubboProperties;
import com.epik.dubbo.boot.config.ProtocolProperties;
import com.epik.dubbo.rest.boot.contant.DubboxContant;

@ConfigurationProperties(prefix="dubbo")
public class DubboxProperties extends AbstractDubboProperties{
	
	public DubboxProperties(){
		initDefaultApplication();
    	initDefaultRegistry();
    	initDefaultMonitor();
    	initDefaultProtocol();
    	initDefaultProvider();
    	initDefaultModule();
	}
	
	/**
	 * 初始化rest应用缺省配置
	 */
	protected void initDefaultApplication(){
		ApplicationConfig application = new ApplicationConfig(DubboxContant.DEFAULT_DUBBOX_APPLICATION_PREFIX + System.currentTimeMillis());
		setApplication(application);
	}
	
	/**
	 * 初始化rest协议缺省配置
	 */
	protected void initDefaultProtocol(){
		ProtocolProperties protocol = new ProtocolProperties();
    	protocol.initRestProtocol().setDefault(true);;
    	setProtocol(protocol);
	}
}
