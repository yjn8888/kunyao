package com.epik.util.json.filter;

/**
 * Copyright 2012-2013 hanhua.com
 * File Name：FilterConfigurations.java
 *
 * ID: $Id$
 * Revision: $Rev$
 * Created: 2013-5-16
 * Created By: liuwenlong
 * Last modified: $Date$
 * Last modified by: $Author$
 */


/**
 * 此类描述的是：TODO:
 * @author: liuwenlong
 * @since : 2013-5-16
 */
public class FilterConfigurations{

    private Configuration[] configurations;
    
    public FilterConfigurations(Configuration...configurations){
        this.configurations = configurations;
    }

    /**
     * configurations
     *
     * @return  the configurations
     * @since   2013-5-16
     */
    public Configuration[] getConfigurations() {
        return configurations;
    }

    /**
     * @param configurations the configurations to set
     */
    public void setConfigurations(Configuration[] configurations) {
        this.configurations = configurations;
    }
    
}
