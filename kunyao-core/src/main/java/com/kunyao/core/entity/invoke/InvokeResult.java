package com.kunyao.core.entity.invoke;

import java.io.Serializable;

/**
 * 
 * 此类描述的是：REST服务统一响应结果
 * @author: yuanjaining@hanhua.com
 * @since : 2015年6月24日
 */
@Deprecated
public class InvokeResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 1 failed 0 success
	 */
	private String resultCode = "1";
	
	/**
	 * 结果信息
	 */
	private String resultMessage;
	
    private String bizResultMessage;
    
    private String exception;
    
	/**
	 * 结果数据
	 */
	private Object data;
	
	private String invokeId;
	
    public String getException() {
        return exception;
    }


    public void setException(String exception) {
        this.exception = exception;
    }


    public String getBizResultMessage() {
        return bizResultMessage;
    }


    public void setBizResultMessage(String bizResultMessage) {
        this.bizResultMessage = bizResultMessage;
    }
	public String getInvokeId() {
        return invokeId;
    }


    public void setInvokeId(String invokeId) {
        this.invokeId = invokeId;
    }


    /**
	 * 本次请求的ID
	 */
//	private String requestId;
	
    public InvokeResult() {
        super();
        // TODO Auto-generated constructor stub
    }


    public InvokeResult(String resultCode, String resultMessage, Object data) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.bizResultMessage = resultMessage;
        this.data = data;
    }
    
    public InvokeResult(String resultCode, String resultMessage, String bizResultMessage, Object data) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.bizResultMessage = bizResultMessage;
        this.data = data;
    }
    
//    public InvokeResult(String resultCode, String resultMessage, Object data,String requestId) {
//        super();
//        this.resultCode = resultCode;
//        this.resultMessage = resultMessage;
//        this.data = data;
//        this.requestId = requestId;
//    }


    public String getResultCode() {
        return resultCode;
    }


    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }


    public String getResultMessage() {
        return resultMessage;
    }


    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }


    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }


//    public String getRequestId() {
//        return requestId;
//    }
//
//
//    public void setRequestId(String requestId) {
//        this.requestId = requestId;
//    }

}
