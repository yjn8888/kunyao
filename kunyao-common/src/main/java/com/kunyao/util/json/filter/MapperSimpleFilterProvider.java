package com.epik.util.json.filter;

/**
 * Copyright 2012-2013 hanhua.com
 * File Name：MapperSimpleFilterProvider.java
 *
 * ID: $Id$
 * Revision: $Rev$
 * Created: 2013-6-1
 * Created By: liuwenlong
 * Last modified: $Date$
 * Last modified by: $Author$
 */

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 此类描述的是：TODO:
 * @author: liuwenlong
 * @since : 2013-6-1
 */
public class MapperSimpleFilterProvider {

    private ObjectMapper objectMapper;
    private JsonSimpleFilterProvider jsonSimpleFilterProvider;
    
    public MapperSimpleFilterProvider(){
        
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JsonSimpleFilterProvider getJsonSimpleFilterProvider() {
        return jsonSimpleFilterProvider;
    }

    public void setJsonSimpleFilterProvider(
            JsonSimpleFilterProvider jsonSimpleFilterProvider) {
        this.jsonSimpleFilterProvider = jsonSimpleFilterProvider;
    }
    
}
