package com.epik.util.json.filter;

/**
 * Copyright 2012-2013 hanhua.com
 * File Name：FilterOutConfiguration.java
 *
 * ID: $Id$
 * Revision: $Rev$
 * Created: 2013-5-16
 * Created By: liuwenlong
 * Last modified: $Date$
 * Last modified by: $Author$
 */


/**
 * 此类描述的是：TODO:不期望需要设置的属性集合
 * @author: liuwenlong
 * @since : 2013-5-16
 */
public class FilterOutConfiguration implements Configuration{

    /**
     * 传入domain，需要被过滤的对象
     */
    private Class<?> clz;
    /**
     * 如果用targetClass形式，需要在当前类，写上需要哪些属性，并用于过滤clz中的字符串
     */
    private String[] classProperties;
    /**
     * 需要过滤的目标类
     */
    private Class<?> targetClass;
    /**
     * 忽略掉被给的ignoreAudit数组，显示其它所有的字符串
     */
    private Boolean ignoreAudit =new Boolean(Boolean.FALSE);
    
    public FilterOutConfiguration(Class<?> clz,String...classProperties){
        this.clz = clz;
        this.classProperties = classProperties;
    }
    
    public FilterOutConfiguration(Class<?> clz){
        this.clz = clz;
    }
    
    public FilterOutConfiguration(Class<?> clz,Class<?> targetClass){
        this.clz = clz;
        this.targetClass = targetClass;
    }

    /**
     * clz
     *
     * @return  the clz
     * @since   2013-5-16
     */
    public Class<?> getClz() {
        return clz;
    }

    /**
     * <h1>传入domain，需要被过滤的对象</h1>
     * @param clz the clz to set
     */
    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    /**
     * classProperties
     *
     * @return  the classProperties
     * @since   2013-5-16
     */
    public String[] getClassProperties() {
        return classProperties;
    }

    /**<h1>如果用targetClass形式，需要在当前类，写上需要哪些属性，并用于过滤clz中的字符串</h1>
     * @param classProperties the classProperties to set
     */
    public void setClassProperties(String[] classProperties) {
        this.classProperties = classProperties;
    }

    /**
     * targetClass
     *
     * @return  the targetClass
     * @since   2013-5-16
     */
    public Class<?> getTargetClass() {
        return targetClass;
    }

    /**
     *<h1>需要过滤的目标类</h1>
     * @param targetClass the targetClass to set
     */
    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public Boolean getIgnoreAudit() {
        return ignoreAudit;
    }

    public void setIgnoreAudit(Boolean ignoreAudit) {
        this.ignoreAudit = ignoreAudit;
    }
}
