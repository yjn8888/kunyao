package com.kunyao.core.entity.access;

import java.io.Serializable;


/**
 * 此类描述的是：基础访问信息值对象:
 * @author: zhangrongbinbj@hanhua.com
 * @since : 2015年7月24日
 */
public class BaseAccessInfo implements Serializable {
    
    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category serialVersionUID 
     * @since: 2015年7月24日
     */
    private static final long serialVersionUID = -3807038112895698847L;


    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category invokeInfo 调用信息
     * @since: 2015年7月24日
     */
    private BaseInvokeInfo invokeInfo;
    
    
    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category sourceInfo 来源信息
     * @since: 2015年7月24日
     */
    private BaseSourceInfo sourceInfo;
    
    
    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category userInfo 用户信息
     * @since: 2015年7月24日
     */
    private BaseUserInfo userInfo;
    
    
    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category requestInfo 请求信息
     * @since: 2015年7月24日
     */
    private BaseRequestInfo requestInfo;
    
    
    /**
     * @author:zhangrongbinbj@hanhua.com
     * @since: 2015年7月24日
     * 
     * @category:
     * @param invokeInfo
     * @param sourceInfo
     * @param userInfo
     */
    public BaseAccessInfo(BaseInvokeInfo invokeInfo, BaseSourceInfo sourceInfo, 
            BaseUserInfo userInfo, BaseRequestInfo reuestInfo) {
        setInvokeInfo(invokeInfo);
        setSourceInfo(sourceInfo);
        setUserInfo(userInfo);
        setRequestInfo(reuestInfo);
    }
    public BaseAccessInfo() {
        // TODO Auto-generated constructor stub
    }

    public BaseRequestInfo getRequestInfo() {
        return requestInfo;
    }

    private void setRequestInfo(BaseRequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    public BaseInvokeInfo getInvokeInfo() {
        return invokeInfo;
    }


    private void setInvokeInfo(BaseInvokeInfo invokeInfo) {
        this.invokeInfo = invokeInfo;
    }


    public BaseSourceInfo getSourceInfo() {
        return sourceInfo;
    }


    private void setSourceInfo(BaseSourceInfo sourceInfo) {
        this.sourceInfo = sourceInfo;
    }


    public BaseUserInfo getUserInfo() {
        return userInfo;
    }


    private void setUserInfo(BaseUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "BaseAccessInfo [invokeInfo=" + invokeInfo + ", sourceInfo=" + sourceInfo + ", userInfo=" + userInfo
                + ", requestInfo=" + requestInfo + "]";
    }
    
}
