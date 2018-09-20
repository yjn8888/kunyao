/**
 * 文件名：PackageUtil.java
 *
 * 版本信息：
 * 创建日期：2013-3-30
 * @version 1.0
 * @author: liuwenlong@hanhua.com
 * Copyright (c) 2012-2013 hanhua.com,Inc. All Rights Reserved.
 *
 */

package com.epik.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

/**
 * 此类描述的是：遍历包下的所有类
 * @author: liuwenlong@hanhua.com
 * @version: 2013-3-30 下午4:18:03 
 */

public class PackageUtil {

    private static final Logger logger = Logger.getLogger(PackageUtil.class);

    /**
     * 获取某包下（包括该包的所有子包）所有类
     * @param packageName 包名
     * @return 类的完整名称
     */
    public static List<String> getClassName(String packageName) {
        return getClassName(packageName, true);
    }

    /**
     * 获取某包下所有类
     * @param packageName 包名
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    public static List<String> getClassName(String packageName, boolean childPackage) {
        List<String> fileNames = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", "/");
        URL url = loader.getResource(packagePath);
        if (url != null) {
            String type = url.getProtocol();
            if (type.equals("file")) {
                fileNames = getClassNameByFile(url.getPath(), null, childPackage);
            } else if (type.equals("jar")) {
                fileNames = getClassNameByJar(url.getPath(), childPackage);
            }
        }
        if(fileNames == null){
            fileNames = new ArrayList<String>();
        }if (ServerUtil.isTomcat()) {
            fileNames.addAll(getClassNameByJars(((URLClassLoader) loader).getURLs(), packagePath, childPackage));
        }
        return fileNames;
    }

    /**
     * 从项目文件获取某包下所有类
     * @param filePath 文件路径
     * @param className 类名集合
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                if (childPackage) {
                    myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage));
                }
            } else {
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(".class")) {
                    if (ServerUtil.isTomcat()) {
                        if (childFilePath.indexOf("\\test-classes") > 0) {
                            childFilePath = childFilePath.substring(childFilePath.indexOf("\\test-classes") + 14,
                                    childFilePath.lastIndexOf("."));
                        } else {
                            childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9,
                                    childFilePath.lastIndexOf("."));
                        }
                        childFilePath = childFilePath.replace("\\", ".");
                    } else if (ServerUtil.isWebSphere()) {
                        childFilePath = childFilePath.substring(childFilePath.indexOf("classes") + 8,
                                childFilePath.lastIndexOf("."));
                        childFilePath = childFilePath.replace("/", ".");
                    } else {
                        childFilePath = childFilePath.substring(childFilePath.indexOf("classes") + 8, childFilePath.lastIndexOf("."));
                        childFilePath = childFilePath.replace("\\", ".");
                        childFilePath = childFilePath.replace("/", ".");
                    }
                    myClassName.add(childFilePath);
                }
            }
        }

        return myClassName;
    }

    /**
     * 从jar获取某包下所有类
     * @param jarPath jar文件路径
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    private static List<String> getClassNameByJar(String jarPath, boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        String[] jarInfo = jarPath.split("!");
        String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
        String packagePath = jarInfo[1].substring(1);
        JarFile jarFile = null;
        try {
        	jarFile = new JarFile(jarFilePath);
            Enumeration<JarEntry> entrys = jarFile.entries();
            while (entrys.hasMoreElements()) {
                JarEntry jarEntry = entrys.nextElement();
                String entryName = jarEntry.getName();
                if (entryName.endsWith(".class")) {
                    if (childPackage) {
                        if (entryName.startsWith(packagePath)) {
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                            myClassName.add(entryName);
                        }
                    } else {
                        int index = entryName.lastIndexOf("/");
                        String myPackagePath;
                        if (index != -1) {
                            myPackagePath = entryName.substring(0, index);
                        } else {
                            myPackagePath = entryName;
                        }
                        if (myPackagePath.equals(packagePath)) {
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                            myClassName.add(entryName);
                        }
                    }
                }
            }
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }finally{
        	if(jarFile!=null){
        		try {
					jarFile.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
        	}
        }
        return myClassName;
    }

    /**
     * 从所有jar中搜索该包，并获取该包下所有类
     * @param urls URL集合
     * @param packagePath 包路径
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    private static List<String> getClassNameByJars(URL[] urls, String packagePath, boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        if (urls != null) {
            for (int i = 0; i < urls.length; i++) {
                URL url = urls[i];
                String urlPath = url.getPath();
                // 不必搜索classes文件夹
                if (urlPath.endsWith("classes/")) {
                    continue;
                }
                String jarPath = urlPath + "!/" + packagePath;
                myClassName.addAll(getClassNameByJar(jarPath, childPackage));
            }
        }
        return myClassName;
    }

    /**
     *
     * 从所有jar中搜索该包，并获取该包下所有类
     * @category
     * @author: huangfupan
     * @since: 2014-4-8
     * @param urls
     * @param packagePath
     * @param childPackage
     * @return
     */
    @SuppressWarnings("unused")
	private static List<String> getClassNameByJarsWithPath(String[] urls, String packagePath, boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        if (urls != null) {
            for (int i = 0; i < urls.length; i++) {
                String urlPath = urls[i];
                // 不必搜索classes文件夹
                if (urlPath.endsWith("classes/")) {
                    continue;
                }
                String jarPath = urlPath + "!/" + packagePath;
                myClassName.addAll(getClassNameByJar(jarPath, childPackage));
            }
        }
        return myClassName;
    }

}
