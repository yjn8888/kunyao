package com.epik.util.json.filter;

/**
 * Copyright 2012-2013 hanhua.com
 * File Name：Configuration.java
 *
 * ID: $Id$
 * Revision: $Rev$
 * Created: 2013-5-16
 * Created By: liuwenlong
 * Last modified: $Date$
 * Last modified by: $Author$
 */


/**
 * 此类描述的是：TODO:配置动态过滤json格式的数据
 * @see com.hanhua.vap.json.FilterOutConfiguration,com.hanhua.vap.json.FilterInConfiguration
 * @author: liuwenlong
 * @since : 2013-5-16
 */
public interface Configuration {
    /**
     * 添加需要过滤的audit字段
     */
    public String[] ignoreAudit = {"createdDate","lastModifiedBy","lastModifiedDate",""};
    /**
     * 运行时，动态添加的字段
     */
    public String[] jId = {"jId"}; 
}
