package com.kunyao.core.entity.factory;

import com.kunyao.core.entity.invoke.InvokeResult;
import com.kunyao.core.entity.invoke.InvokeResultWithGenericType;
import com.kunyao.core.exception.BusinessException;


/**
 * 此类描述的是：InvokeResult工厂类
 * @author: zhangrongbinbj@hanhua.com
 * @since : 2015年8月6日
 */
@SuppressWarnings("deprecation")
public class InvokeResultFactory {
    
	public static InvokeResult getSuccessInvokeResultWithoutlData() {
        return new InvokeResult("0000", "SUCCESS", null);
    }
    
    public static <T> InvokeResultWithGenericType<T> getSuccessResult() {
        return new InvokeResultWithGenericType<T>("0000", "SUCCESS", null);
    }
    
    public static <T> InvokeResultWithGenericType<T> getSuccessResult(T data) {
        return new InvokeResultWithGenericType<T>("0000", "SUCCESS", data);
    }
    
    public static <T> InvokeResultWithGenericType<T> getFailResult() {
        return new InvokeResultWithGenericType<T>("-1", "系统错误，请联系管理员",null);
    }
    
    public static InvokeResult getSuccessInvokeResult(Object data) {
        return new InvokeResult("0000", "SUCCESS", data);
    }
    
    public static InvokeResult getGeneralFailedInvokeResultWithoutData() {
        return new InvokeResult("-1", "系统错误，请联系管理员", null);
    }

    public static InvokeResult getBusinessExceptionInvokeResult(BusinessException e) {
        return new InvokeResult(e.getBusinessErrorCode(), e.getBusinessErrorMessage(), null);
    }
}