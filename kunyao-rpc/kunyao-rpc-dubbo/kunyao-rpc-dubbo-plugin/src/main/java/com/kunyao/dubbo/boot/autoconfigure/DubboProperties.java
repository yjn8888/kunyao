package com.kunyao.dubbo.boot.autoconfigure;


import org.apache.dubbo.config.RegistryConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import static com.kunyao.dubbo.boot.contant.DubboContant.DEFAULT_REGISTRY_ADDRESS;

@ConfigurationProperties(
		prefix = "dubbo"
)
public class DubboProperties {

	private RegistryConfig registry = new RegistryConfig(DEFAULT_REGISTRY_ADDRESS);

	public RegistryConfig getRegistry() {
		return registry;
	}

	public void setRegistry(RegistryConfig registry) {
		this.registry = registry;
	}
}
