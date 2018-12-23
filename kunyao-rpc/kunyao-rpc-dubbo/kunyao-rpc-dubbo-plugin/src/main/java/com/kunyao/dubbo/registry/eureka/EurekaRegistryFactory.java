package com.kunyao.dubbo.registry.eureka;


import org.apache.dubbo.common.URL;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.RegistryFactory;

public class EurekaRegistryFactory implements RegistryFactory {

	@Override
	public Registry getRegistry(URL url) {
		return new EurekaRegistry(url);
	}

}
