package com.kunyao.dubbo.boot.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dubbo")
public class DubboProperties extends AbstractDubboProperties {
	public DubboProperties() {
		initDefaultApplication();
    	initDefaultRegistry();
    	initDefaultMonitor();
    	initDefaultProtocol();
    	initDefaultProvider();
    	initDefaultModule();
	}
}
