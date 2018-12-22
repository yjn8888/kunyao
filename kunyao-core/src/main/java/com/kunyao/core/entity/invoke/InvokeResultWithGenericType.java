package com.kunyao.core.entity.invoke;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;



/**
 * 此类描述的是：泛型的invokeResult类:
 * @author: zhangrongbinbj@hanhua.com
 * @since : 2015年7月31日
 */
public class InvokeResultWithGenericType<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * @author: zhangrongbinbj@hanhua.com
	 * @category invokeId 本次请求的ID，一般由访问方提供，如果访问方没提供，系统会自动生成
	 * @since: 2015年7月31日
	 */
    @ApiModelProperty(
            value = "本次调用的Id，uuid格式，需要client端调用时提供，如果未提供，系统自动生成", 
            example = "AA166178-2A7C-4E3E-96FA-29D19142F0AE", 
            required = false)
	private String invokeId;
	/**
	 * @author: zhangrongbinbj@hanhua.com
	 * @category resultCode -1 system fail 2000 business fail 0000 success
	 * @since: 2015年7月31日
	 */
    @ApiModelProperty(
            value = "调用结果代码", 
            example = "0000", 
            required = false)
	private String resultCode = "-1";
	
	/**
	 * @author: zhangrongbinbj@hanhua.com
	 * @category resultMessage 结果信息
	 * @since: 2015年7月31日
	 */
    @ApiModelProperty(
            value = "调用结果信息", 
            example = "SUCCESS", 
            required = false)
	private String resultMessage = "系统错误，请联系管理员";

    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category exception 错误异常信息
     * @since: 2015年7月31日
     */
    @ApiModelProperty(
            value = "错误异常信息", 
            example = "java.lang.NullPointException .....", 
            required = false)
    private String exception;
    
    /**
     * @author: zhangrongbinbj@hanhua.com
     * @category bizResultMessage 返回给用户提示的返回信息
     * @since: 2015年7月31日
     */
    @ApiModelProperty(
            value = "返回给用户提示的返回信息", 
            example = "尊敬的用户：您无权此操作", 
            required = false)
    private String bizResultMessage;
	
	public String getBizResultMessage() {
        return bizResultMessage;
    }


    public void setBizResultMessage(String bizResultMessage) {
        this.bizResultMessage = bizResultMessage;
    }


    /**
	 * @author: zhangrongbinbj@hanhua.com
	 * @category data 结果数据
	 * @since: 2015年7月31日
	 */
    @ApiModelProperty(
            value = "结果数据", 
            example = "", 
            required = false)
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


    public InvokeResultWithGenericType() {
        super();
    }


    public InvokeResultWithGenericType(String resultCode, String resultMessage, T data) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.bizResultMessage = resultMessage;
        this.data = data;
    }
    
    public InvokeResultWithGenericType(String resultCode, String resultMessage, String bizResultMessage, T data) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.bizResultMessage = bizResultMessage;
        this.data = data;
    }
    
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
        this.bizResultMessage = resultMessage;
    }


    public T getData() {
        return data;
    }


    public void setData(T data) {
        this.data = data;
    }

}