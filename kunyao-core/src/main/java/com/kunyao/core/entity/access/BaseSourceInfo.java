package com.kunyao.core.entity.access;

import java.io.Serializable;


/**
 * 此类描述的是：访问来源值对象:
 * @author: zhangrongbinbj@hanhua.com
 * @since : 2015年7月24日
 */
public class BaseSourceInfo implements Serializable{
    
    
    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category serialVersionUID 
     * @since: 2015年7月24日
     */
    private static final long serialVersionUID = -1553784168947253110L;

    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category sourceSystem 来源系统
     * @since: 2015年7月24日
     */
    String sourceSystem;
    
    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category sourceIp 来源IP
     * @since: 2015年7月24日
     */
    String sourceIp;

    /**
     * @author:zhangrongbinbj@hanhua.com
     * @since: 2015年7月24日
     * 
     * @category:
     * @param sourceSystem
     * @param sourceIp
     */
    
    public BaseSourceInfo() {
        // TODO Auto-generated constructor stub
    }
    
    public BaseSourceInfo(String sourceSystem, String sourceIp) {
        setSourceSystem(sourceSystem);
        setSourceIp(sourceIp);
    }
    private void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }
    private void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }
    public String getSourceSystem() {
        return sourceSystem;
    }
    public String getSourceIp() {
        return sourceIp;
    }
    @Override
    public String toString() {
        return "BaseSourceInfo [sourceSystem=" + sourceSystem + ", sourceIp=" + sourceIp + "]";
    }
}
