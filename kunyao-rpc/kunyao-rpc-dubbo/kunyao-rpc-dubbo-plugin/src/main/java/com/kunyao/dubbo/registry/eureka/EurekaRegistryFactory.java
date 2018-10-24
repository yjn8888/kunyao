package com.kunyao.dubbo.registry.eureka;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;

public class EurekaRegistryFactory implements RegistryFactory{

	@Override
	public Registry getRegistry(URL url) {
		return new EurekaRegistry(url);
	}

}
