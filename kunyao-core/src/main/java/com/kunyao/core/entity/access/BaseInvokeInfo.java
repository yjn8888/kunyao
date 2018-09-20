package com.kunyao.core.entity.access;

import java.io.Serializable;


/**
 * 此类描述的是：BaseInvokeInfo值对象
 * @author: zhangrongbinbj@hanhua.com
 * @since : 2015年8月6日
 */
public class BaseInvokeInfo implements Serializable{
    
    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category serialVersionUID 
     * @since: 2015年7月24日
     */
    private static final long serialVersionUID = -8989014217004195315L;


    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category invokeId 调用ID，一般在客户端访问时生成
     * @since: 2015年7月24日
     */
    private String invokeId;
    
    
    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category accessToken 访问token，用于认证
     * @since: 2015年7月24日
     */
    private String accessToken;
    
    
    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category accessStartTime 访问开始时间
     * @since: 2015年7月28日
     */
    private Long accessStartTime;
    
    
    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category accessEndTime 服务结束时间
     * @since: 2015年7月28日
     */
    private Long accessEndTime;
    public BaseInvokeInfo() {
        // TODO Auto-generated constructor stub
    }
    public Long getAccessStartTime() {
        return accessStartTime;
    }

    public void setAccessStartTime(Long accessStartTime) {
        this.accessStartTime = accessStartTime;
    }

    public Long getAccessEndTime() {
        return accessEndTime;
    }

    public void setAccessEndTime(Long accessEndTime) {
        this.accessEndTime = accessEndTime;
    }

    /**
     * @author:zhangrongbinbj@hanhua.com
     * @since: 2015年7月24日
     * 
     * @category:
     * @param invokeId
     * @param accessToken
     */
    public BaseInvokeInfo(String invokeId, String accessToken) {
        setAccessToken(accessToken);
        setInvokeId(invokeId);
    }

    public String getInvokeId() {
        return invokeId;
    }

    public void setInvokeId(String invokeId) {
        this.invokeId = invokeId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    private void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "BaseInvokeInfo [invokeId=" + invokeId + ", accessToken=" + accessToken + "]";
    }
}
