/**
 * Copyright 2012-2013 hanhua.com
 * File Name：JsonFilterFactory.java
 *
 * ID: $Id$
 * Revision: $Rev$
 * Created: 2013-5-16
 * Created By: liuwenlong
 * Last modified: $Date$
 * Last modified by: $Author$
 */
package com.epik.util.json.filter;

import java.text.SimpleDateFormat;

import org.apache.commons.pool.BasePoolableObjectFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.epik.util.DateTimeUtil;

/**
 * 此类描述的是：TODO:
 * 
 * @author: liuwenlong
 * @since : 2013-5-16
 */
@SuppressWarnings("rawtypes")
public class JsonFilterFactory extends BasePoolableObjectFactory {

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.commons.pool.BasePoolableObjectFactory#makeObject()
     */
    @Override
    public Object makeObject() throws Exception {
        MapperSimpleFilterProvider mapperSimpleFilterProvider=new MapperSimpleFilterProvider();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonSimpleFilterProvider jsfp = new JsonSimpleFilterProvider();
        for (String f : JsonFilterConfig.filters) {
            jsfp.addFilter(f, SimpleBeanPropertyFilter.serializeAllExcept());
        }
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.DATE_TIME_FORMAT));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapperSimpleFilterProvider.setJsonSimpleFilterProvider(jsfp);
        mapperSimpleFilterProvider.setObjectMapper(objectMapper);
        return mapperSimpleFilterProvider;
    }
}
