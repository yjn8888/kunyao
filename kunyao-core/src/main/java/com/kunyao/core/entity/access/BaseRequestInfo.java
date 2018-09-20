package com.kunyao.core.entity.access;

import java.io.Serializable;


/**
 * 此类描述的是：BaseRequestInfo值对象
 * @author: zhangrongbinbj@hanhua.com
 * @since : 2015年8月6日
 */
public class BaseRequestInfo implements Serializable {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @author: zhangrongbinbj@hanhua.com
     * @category requestUrl 
     * @since: 2015年7月24日
     */
    private String requestUrl;
    
    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category requestMethod 
     * @since: 2015年7月24日
     */
    private String requestMethod;
    
    public BaseRequestInfo() {
        // TODO Auto-generated constructor stub
    }
    
    public BaseRequestInfo(String requestUrl, String requestMethod) {
        super();
        this.requestUrl = requestUrl;
        this.requestMethod = requestMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    @Override
    public String toString() {
        return "BaseRequestInfo [requestUrl=" + requestUrl + ", requestMethod=" + requestMethod + "]";
    }
    
    
}
