package com.kunyao.dubbo.registry.eureka;


import org.apache.dubbo.common.URL;
import org.apache.dubbo.registry.NotifyListener;
import org.apache.dubbo.registry.support.FailbackRegistry;

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
	public void doRegister(URL url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUnregister(URL url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSubscribe(URL url, NotifyListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUnsubscribe(URL url, NotifyListener listener) {
		// TODO Auto-generated method stub
		
	}

}
