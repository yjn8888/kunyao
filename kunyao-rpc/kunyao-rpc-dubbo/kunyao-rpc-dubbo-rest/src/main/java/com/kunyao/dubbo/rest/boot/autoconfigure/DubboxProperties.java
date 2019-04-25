package com.kunyao.dubbo.rest.boot.autoconfigure;

import com.kunyao.dubbo.rest.boot.contant.DubboxContant;

import org.apache.dubbo.config.ApplicationConfig;
import com.kunyao.dubbo.boot.autoconfigure.AbstractDubboProperties;
import com.kunyao.dubbo.boot.autoconfigure.ProtocolProperties;

public class DubboxProperties extends AbstractDubboProperties{
	
	public DubboxProperties(){
		initDefaultApplication();
    	initDefaultRegistry();
    	initDefaultMonitor();
    	initDefaultProtocol();
    	initDefaultProvider();
	}
	
	/**
	 * 初始化rest应用缺省配置
	 */
	protected void initDefaultApplication(){
		this.application = new ApplicationConfig(DubboxContant.DEFAULT_DUBBOX_APPLICATION_PREFIX + System.currentTimeMillis());
	}
	
	/**
	 * 初始化rest协议缺省配置
	 */
	protected void initDefaultProtocol(){
    	this.protocol.initRestProtocol().setDefault(true);;
	}
}
