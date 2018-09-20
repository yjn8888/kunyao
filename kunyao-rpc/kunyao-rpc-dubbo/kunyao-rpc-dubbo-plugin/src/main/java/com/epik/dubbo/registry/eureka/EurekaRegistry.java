package com.epik.dubbo.registry.eureka;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.registry.NotifyListener;
import com.alibaba.dubbo.registry.support.FailbackRegistry;

public class EurekaRegistry extends FailbackRegistry {

	public EurekaRegistry(URL url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void doRegister(URL url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doUnregister(URL url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doSubscribe(URL url, NotifyListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doUnsubscribe(URL url, NotifyListener listener) {
		// TODO Auto-generated method stub
		
	}

}
