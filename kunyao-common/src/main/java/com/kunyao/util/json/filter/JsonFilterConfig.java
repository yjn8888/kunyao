package com.epik.util.json.filter;

/**
 * Copyright 2012-2013 hanhua.com
 * File Name：JsonFilterConfig.java
 *
 * ID: $Id$
 * Revision: $Rev$
 * Created: 2013-5-16
 * Created By: liuwenlong
 * Last modified: $Date$
 * Last modified by: $Author$
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.epik.util.PackageUtil;


/**
 * 此类描述的是：TODO:jsonfilter配置规则，扫描包结构，创建jsonfilter池和释放池子
 * @author: liuwenlong
 * @since : 2013-5-16
 */
public class JsonFilterConfig {

    private final static Logger log =Logger.getLogger(JsonFilterConfig.class);
            
    public static GenericObjectPool<Object> pool = new GenericObjectPool<Object>();
    public static List<String> filters=new ArrayList<String>();
    
    private static String AUTHMODEL = "com.hanhua.vap.auth.model";
    
    /**
     * TODO:(此方法的描述)<h1>初始化json序列化，1.扫描包目录，2.把jsonfilter添加到provider池子</h1>
     * @category
     * @author: liuwenlong
     * @since: 2013-5-18
     * @param packageName
     */
    public static void initJsonFilterConfig(String packageName){
        JsonFilterConfig.scanJsonFilter(packageName);
        JsonFilterConfig.initSimpleFilterProviderPool();
    }
    
    /**
     * 
     * TODO:(此方法的描述) 扫描jsonfilter的包结构
     * @category
     * @author: liuwenlong
     * @since: 2013-5-18
     * @param packageName
     */
    public static void scanJsonFilter(String...packageNames){
        log.info("packageNames..."+packageNames);
        for(String packageName:packageNames){
            List<String> classNames = null;
            if(AUTHMODEL.equals(packageName)){
                classNames = new ArrayList<String>();
                classNames.add("com.hanhua.vap.auth.model.Operation");
                classNames.add("com.hanhua.vap.auth.model.Permission");
                classNames.add("com.hanhua.vap.auth.model.Resource");
            }else{
                classNames = PackageUtil.getClassName(packageName);
            }
            for(String className:classNames){
                try {
                    Class<?> clz = Class.forName(className);
                    JsonFilter jsonFilter = (JsonFilter) clz.getAnnotation(JsonFilter.class);
                    if(jsonFilter!=null){
                        filters.add(jsonFilter.value());
                    }
                } catch (ClassNotFoundException e) {
                    log.info(e.getMessage()+"............."+e);
                }
            }
        }
    }
    /**
     * 
     * TODO:(此方法的描述)初始化JsonSimpleFilterProvider池，创建多个连接
     * @category
     * @author: liuwenlong
     * @since: 2013-5-18
     */
    @SuppressWarnings({ "deprecation", "unchecked" })
	public static void initSimpleFilterProviderPool(){
        pool.setFactory(new JsonFilterFactory());
        pool.setMaxActive(5000);
        pool.setMaxIdle(50);
        pool.setMaxWait(60000);
        pool.setTestOnBorrow(false);
        pool.setTestOnReturn(true);
        pool.setTestWhileIdle(true);
        pool.setMinEvictableIdleTimeMillis(1800000);
        pool.setTimeBetweenEvictionRunsMillis(60000);
    }
    /**
     * 
     * TODO:(此方法的描述)重新设置jsonfilter，并把JsonSimpleFilterProvider对象返回到池子中
     * @category
     * @author: liuwenlong
     * @since: 2013-5-18
     * @param filterId
     * @param jsonSimpleFilterProvider
     * @throws Exception
     */
    public static void resetWith(List<String> filterIds,JsonSimpleFilterProvider jsonSimpleFilterProvider,MapperSimpleFilterProvider mapperSimpleFilterProvider,String tempExcept,Map<String,String> map) throws Exception{
        if(CollectionUtils.isNotEmpty(filterIds)){
            for(String filterId:filterIds){
                jsonSimpleFilterProvider.removeFilter(filterId);
                //默认FILTER设置为序列化所有字段
                jsonSimpleFilterProvider.addFilter(filterId, SimpleBeanPropertyFilter.serializeAllExcept());
                /*if(JSONUtil.FILTEROUTALLEXCEPT.equalsIgnoreCase(map.get(filterId))){
                    jsonSimpleFilterProvider.addFilter(filterId, SimpleBeanPropertyFilter.filterOutAllExcept());
                }else if(JSONUtil.SERIALIZEALLEXCEPT.equalsIgnoreCase(map.get(filterId))){
                    jsonSimpleFilterProvider.addFilter(filterId, SimpleBeanPropertyFilter.serializeAllExcept());
                }*/
            }
        }
        JsonFilterConfig.pool.returnObject(mapperSimpleFilterProvider);
    }
}
