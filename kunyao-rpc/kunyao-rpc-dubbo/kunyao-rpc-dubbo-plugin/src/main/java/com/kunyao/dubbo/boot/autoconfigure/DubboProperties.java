package com.kunyao.dubbo.boot.autoconfigure;


import org.apache.dubbo.config.RegistryConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import static com.kunyao.dubbo.boot.contant.DubboContant.DEFAULT_REGISTRY_ADDRESS;

@ConfigurationProperties(
		prefix = "dubbo"
)
public class DubboProperties {

	private RegistryConfig registry = new RegistryConfig(DEFAULT_REGISTRY_ADDRESS);

	private int globalTimeout = 30000;

	public RegistryConfig getRegistry() {
		return registry;
	}

	public void setRegistry(RegistryConfig registry) {
		this.registry = registry;
	}

	public int getGlobalTimeout() {
		return globalTimeout;
	}

	public void setGlobalTimeout(int globalTimeout) {
		this.globalTimeout = globalTimeout;
	}
}
