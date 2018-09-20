package com.epik.util.json.filter;

/**
 * Copyright 2012-2013 hanhua.com
 * File Name：JsonSimpleFilterProvider.java
 *
 * ID: $Id$
 * Revision: $Rev$
 * Created: 2013-5-16
 * Created By: liuwenlong
 * Last modified: $Date$
 * Last modified by: $Author$
 */

import java.util.List;

import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * 此类描述的是：TODO:
 * 
 * @author: liuwenlong
 * @since : 2013-5-16
 */
public class JsonSimpleFilterProvider extends SimpleFilterProvider {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int base = 0;
    private int id;
    private String name;
    private List<String> filters;

    public JsonSimpleFilterProvider() {
        this.id = base++;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /**
     * filters
     *
     * @return  the filters
     * @since   2013-5-16
     */
    public List<String> getFilters() {
        return filters;
    }

    /**
     * @param filters the filters to set
     */
    public void setFilters(List<String> filters) {
        this.filters = filters;
    }
    
}
