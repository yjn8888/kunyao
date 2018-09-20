/**
 * Copyright 2012-2013 hanhua.com
 * File Name：ServerUtil.java
 *
 * ID: $Id$
 * Revision: $Rev$
 * Created: 2013-5-28
 * Created By: liuwenlong
 * Last modified: $Date$
 * Last modified by: $Author$
 */
package com.epik.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 此类描述的是：TODO:判断当前使用的server容器
 * 
 * @author: liuwenlong
 * @since : 2013-5-28
 */
public class ServerUtil {
	
	/**
	 * jboss容器启动类
	 */
	private static final String JBOSS_CLASS = "/org/jboss/Main.class";
	/**
	 * tomcat容器启动类
	 */
	private static final String TOMCAT_CLASS = "/org/apache/catalina/startup/Bootstrap.class";
	/**
	 * weblogic容器启动类
	 */
	private static final String WEBLOGIC_CLASS = "/weblogic/Server.class";
	/**
	 * was容器启动类
	 */
	private static final String WEBSPHERE_CLASS = "/com/ibm/websphere/product/VersionInfo.class";

	private static boolean jBoss = false;

	private static boolean tomcat = false;

	private static boolean webLogic = false;

	private static boolean webSphere = false;

	private static boolean windows = false;

	private static boolean linux = false;
	
	private static String host = "127.0.0.1";

	static {
		Class<?> server = ServerUtil.class;
		if (server.getResource(TOMCAT_CLASS) != null) {
			tomcat = true;
		}
		if (server.getResource(WEBSPHERE_CLASS) != null) {
			webSphere = true;
		}
		if (server.getResource(JBOSS_CLASS) != null) {
			jBoss = true;
		}
		if (server.getResource(WEBLOGIC_CLASS) != null) {
			webLogic = true;
		}
		String os = System.getProperty("os.name").toLowerCase();
		if (os.startsWith("win")) {
			windows = true;
		}
		if (os.startsWith("linux")) {
			linux = true;
		}
		
		 InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			host = addr.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static String getHost(){
		return host;
	}

	public static boolean isJBoss() {
		return jBoss;
	}

	public static boolean isTomcat() {
		return tomcat;
	}

	public static boolean isWebLogic() {
		return webLogic;
	}

	public static boolean isWebSphere() {
		return webSphere;
	}

	public static boolean isWindows() {
		return windows;
	}

	public static boolean isLinux() {
		return linux;
	}
}
