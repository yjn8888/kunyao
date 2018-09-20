package com.kunyao.core.entity.cache;

import java.io.Serializable;

import org.joda.time.DateTime;

public class CacheAbleEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /*@com.fasterxml.jackson.annotation.JsonIgnore
    @org.codehaus.jackson.annotate.JsonIgnore*/
    private float periodOfValidity = 0.5F;

   /* @com.fasterxml.jackson.annotation.JsonIgnore
    @org.codehaus.jackson.annotate.JsonIgnore*/
    private DateTime expiredTime;

    public boolean isExpired() {
        if (expiredTime != null) {
            return expiredTime.isBeforeNow();
        }
        return true;
    }

    public CacheAbleEntity() {
        expiredTime = new DateTime().plusMinutes((int) (periodOfValidity * 60));
    }
}
