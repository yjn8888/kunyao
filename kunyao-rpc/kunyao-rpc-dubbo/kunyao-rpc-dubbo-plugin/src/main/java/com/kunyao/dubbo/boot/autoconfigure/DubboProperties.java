package com.kunyao.dubbo.boot.autoconfigure;


import org.springframework.boot.context.properties.ConfigurationProperties;

public class DubboProperties extends AbstractDubboProperties {
	public DubboProperties() {
		initDefaultApplication();
    	initDefaultRegistry();
    	initDefaultMonitor();
    	initDefaultProtocol();
    	initDefaultProvider();
	}
}
