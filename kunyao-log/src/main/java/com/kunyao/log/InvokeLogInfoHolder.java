package com.kunyao.log;



/**
 * 此类描述的是：InvokeLog ThreadLocal
 * @author: zhangrongbinbj@hanhua.com
 * @since : 2015年8月6日
 */
public class InvokeLogInfoHolder {
    private static ThreadLocal<InvokeLogInfo> invokeLogInfoHolder = new ThreadLocal<InvokeLogInfo>();
    
    public static InvokeLogInfo getInvokeLogInfo() {
        return invokeLogInfoHolder.get();
    }
    
    public static void setInvokeLogInfo(InvokeLogInfo invokeLogInfo) {
    	invokeLogInfoHolder.set(invokeLogInfo);
    }
    
    public static void removeInvokeLogInfo() {
    	invokeLogInfoHolder.remove();
    }
}
