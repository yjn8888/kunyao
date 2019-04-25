package com.kunyao.dubbo.boot.contant;

import org.apache.dubbo.common.Constants;

public abstract class DubboContant extends Constants {
	/**
	 * kryo序列化方式
	 */
	public final static String KRYO_REMOTING_SERIALIZATION = "kryo";
	
	/**
	 * dubbo协议默认远程调用的端口
	 */
	public final static Integer DEFAULT_REMOTING_PORT = -1;
	
	/**
	 * dubbo协议默认线程池模式
	 */
	public final static String DEFAULT_REMOTING_THREADPOOL = "fixed";
	
	/**
	 * dubbo协议默认线程池固定大小
	 */
	public final static Integer DEFAULT_REMOTING_THREADS = 100;
	
	/**
	 * dubbo协议默认IO线程池固定大小
	 */
	public final static Integer DEFAULT_REMOTING_IOTHREADS = 9;
	
	/**
	 * dubbo协议服务提供方最大可接受连接数
	 */
	public final static Integer DEFAULT_REMOTING_ACCEPTS = 1000;
	
	/**
	 * 缺省的服务注册中心
	 */
	public final static String DEFAULT_REGISTRY_ADDRESS = "zookeeper://YJN-PC:2182";
	
	/**
	 * 缺省的应用名前缀
	 */
	public final static String DEFAULT_APPLICATION_PREFIX = "KUNYAO-DUBBO-APP-";

	/**
	 * rest协议缺省名称
	 */
	public final static String DEFAULT_REST_PROTOCOL = "rest"; 
	
	/**
	 * rest协议缺省server
	 */
	public final static String DEFAULT_REST_PROTOCOL_SERVER = "tomcat";
	
	/**
	 * rest协议端口
	 */
	public final static Integer DEFAULT_REST_PROTOCOL_PORT = 8888;
	
	/**
	 * rest协议下tomcat服务器提供端所能同时接收的最大HTTP连接数，防止REST server被过多连接撑爆，以作为一种最基本的自我保护机制
	 */
	public final static Integer DEFAULT_REST_PROTOCOL_ACCEPTS = 500;
	
	/**
	 * rest协议默认线程池固定大小
	 */
	public final static Integer DEFAULT_REST_PROTOCOL_THREADS = 500;
	
	/**
	 * rest协议默认contextpath
	 */
	public final static String DEFAULT_REST_PROTOCOL_CONTEXTPATH = "services";
	
	/**
	 * dubbo restapp缺省应用名称
	 */
	public final static String DEFAULT_DUBBOX_APPLICATION_PREFIX = "KUNYAO-DUBBOX-APP-";
	
	/**
	 * dubbo rest
	 */
	public final static String DEFAULT_REST_PROTOCOL_EXTENSION = "com.alibaba.dubbo.rpc.protocol.rest.support.LoggingFilter";
}
