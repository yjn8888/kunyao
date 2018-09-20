package com.kunyao.core.entity.access;

import java.io.Serializable;


/**
 * 此类描述的是：描述登录用户信息:
 * @author: zhangrongbinbj@hanhua.com
 * @since : 2015年7月24日
 */
public class BaseUserInfo implements Serializable{
    
    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category serialVersionUID 
     * @since: 2015年7月24日
     */
    private static final long serialVersionUID = -8437329297665991881L;
    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category appUserId 访问用户在APP中的用户ID，一般为UUID，不做限制
     * @since: 2015年7月24日
     */
    private String appUserId;
    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category appUserName 访问者用户名，一般在APP中注册，或者为系统中的雇员邮箱地址
     * @since: 2015年7月24日
     */
    private String appUserName;
    
    private String ssoToken;
    
    
    /**
     * @author:zhangrongbinbj@hanhua.com
     * @since: 2015年7月24日
     * 
     * @category:
     * @param appUserId
     * @param appUserName
     */
    public BaseUserInfo(String appUserId, String appUserName) {
        setAppUserId(appUserId);
        setAppUserName(appUserName);
    }
    
    public BaseUserInfo(String appUserId, String appUserName, String ssoToken) {
        setAppUserId(appUserId);
        setAppUserName(appUserName);
        setSsoToken(ssoToken);
    }
    
    public BaseUserInfo() {
        // TODO Auto-generated constructor stub
    }
    
    private void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }
    private void setAppUserName(String appUserName) {
        this.appUserName = appUserName;
    }
    public String getAppUserId() {
        return appUserId;
    }
    public String getAppUserName() {
        return appUserName;
    }
    
    public String getSsoToken() {
        return ssoToken;
    }

    private void setSsoToken(String ssoToken) {
        this.ssoToken = ssoToken;
    }

    @Override
    public String toString() {
        return "BaseUserInfo [appUserId=" + appUserId + ", appUserName=" + appUserName + "]";
    }
}
