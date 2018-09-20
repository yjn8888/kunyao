package com.kunyao.core.entity.access;



/**
 * 此类描述的是：AccessInfo ThreadLocal
 * @author: zhangrongbinbj@hanhua.com
 * @since : 2015年8月6日
 */
public class AccessInfoHolder {
    private static ThreadLocal<BaseAccessInfo> accessInfoHolder = new ThreadLocal<BaseAccessInfo>();
    
    public static BaseAccessInfo getAccessInfo() {
        return accessInfoHolder.get();
    }
    
    public static void setAccessInfo(BaseAccessInfo baseAccessInfo) {
        accessInfoHolder.set(baseAccessInfo);
    }
    
    public static void removeAccessInfo() {
    	accessInfoHolder.remove();
    }
}
