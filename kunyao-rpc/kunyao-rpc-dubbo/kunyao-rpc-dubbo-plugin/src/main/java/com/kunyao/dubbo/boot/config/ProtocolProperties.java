package com.kunyao.dubbo.boot.config;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.kunyao.dubbo.boot.contant.DubboContant;

public class ProtocolProperties {
	
	public ProtocolProperties(){
		
	}
	
	private ProtocolConfig dubbo;
	
	private ProtocolConfig rest;
	
	private ProtocolConfig rmi;
	
	private ProtocolConfig hessian;
	
	private ProtocolConfig http;
	
	
	public ProtocolConfig initDubboProtocol(){
		this.dubbo = new ProtocolConfig(DubboContant.DEFAULT_PROTOCOL,DubboContant.DEFAULT_REMOTING_PORT);
    	this.dubbo.setId(dubbo.getName());
    	this.dubbo.setSerialization(DubboContant.KRYO_REMOTING_SERIALIZATION);
    	this.dubbo.setServer(DubboContant.DEFAULT_REMOTING_SERVER);
    	this.dubbo.setClient(DubboContant.DEFAULT_REMOTING_CLIENT);
    	this.dubbo.setCharset(DubboContant.DEFAULT_CHARSET);
    	this.dubbo.setThreadpool(DubboContant.DEFAULT_REMOTING_THREADPOOL);
    	this.dubbo.setThreads(DubboContant.DEFAULT_REMOTING_THREADS);
    	this.dubbo.setIothreads(DubboContant.DEFAULT_REMOTING_IOTHREADS);
    	this.dubbo.setQueues(DubboContant.DEFAULT_QUEUES);
    	this.dubbo.setAccepts(DubboContant.DEFAULT_REMOTING_ACCEPTS);
    	this.dubbo.setBuffer(DubboContant.DEFAULT_BUFFER_SIZE);
    	this.dubbo.setPayload(DubboContant.DEFAULT_PAYLOAD);
    	return this.dubbo;
	}
	
	public ProtocolConfig initRestProtocol(){
		this.rest =new ProtocolConfig(DubboContant.DEFAULT_REST_PROTOCOL,-1);
		this.rest.setId(rest.getName());
		this.rest.setContextpath(DubboContant.DEFAULT_REST_PROTOCOL_CONTEXTPATH);
		this.rest.setKeepAlive(false);
		this.rest.setServer(DubboContant.DEFAULT_REST_PROTOCOL_SERVER);
		this.rest.setThreads(DubboContant.DEFAULT_REST_PROTOCOL_THREADS);
		this.rest.setAccepts(DubboContant.DEFAULT_REST_PROTOCOL_ACCEPTS);
		this.rest.setExtension(DubboContant.DEFAULT_REST_PROTOCOL_EXTENSION);
		this.rest.setPort(DubboContant.DEFAULT_REST_PROTOCOL_PORT);
		return this.rest;
	}
	
	public ProtocolConfig getRest() {
		return rest;
	}

	public void setRest(ProtocolConfig rest) {
		this.rest = rest;
	}

	public ProtocolConfig getDubbo() {
		return dubbo;
	}

	public void setDubbo(ProtocolConfig dubbo) {
		this.dubbo = dubbo;
	}

	public ProtocolConfig getRmi() {
		return rmi;
	}

	public void setRmi(ProtocolConfig rmi) {
		this.rmi = rmi;
	}

	public ProtocolConfig getHessian() {
		return hessian;
	}

	public void setHessian(ProtocolConfig hessian) {
		this.hessian = hessian;
	}

	public ProtocolConfig getHttp() {
		return http;
	}

	public void setHttp(ProtocolConfig http) {
		this.http = http;
	}

}