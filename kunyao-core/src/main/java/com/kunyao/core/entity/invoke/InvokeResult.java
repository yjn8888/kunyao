package com.kunyao.core.entity.invoke;

import com.kunyao.core.entity.base.Entity;


/**
 * 此类描述的是：泛型的invokeResult类:
 */
public class InvokeResult<T> implements Entity {

	private static final long serialVersionUID = 1L;

	private String invokeId;

	private String resultCode = "00000";

	private String resultMessage = "SUCCESS";

    private String exception;

    private String bizResultMessage;
	
	public String getBizResultMessage() {
        return bizResultMessage;
    }


    public void setBizResultMessage(String bizResultMessage) {
        this.bizResultMessage = bizResultMessage;
    }

	private T data;
	
    public String getException() {
        return exception;
    }


    public void setException(String exception) {
        this.exception = exception;
    }


    public String getInvokeId() {
        return invokeId;
    }


    public void setInvokeId(String invokeId) {
        this.invokeId = invokeId;
    }


    public InvokeResult() {
        super();
    }


    public InvokeResult(String resultCode, String resultMessage, T data) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.bizResultMessage = resultMessage;
        this.data = data;
    }
    
    public InvokeResult(String resultCode, String resultMessage, String bizResultMessage, T data) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.bizResultMessage = bizResultMessage;
        this.data = data;
    }
    
    public String getResultCode() {
        return this.resultCode;
    }


    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }


    public String getResultMessage() {
        return this.resultMessage;
    }


    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
        this.bizResultMessage = resultMessage;
    }


    public T getData() {
        return this.data;
    }


    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String getTraceId() {
        return invokeId;
    }
}
