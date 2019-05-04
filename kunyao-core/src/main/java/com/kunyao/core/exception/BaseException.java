package com.kunyao.core.exception;


import lombok.Data;

/* *
 * @author 
 * @version 1.0
 */
@Data
public abstract class BaseException extends RuntimeException {

	private static final long serialVersionUID = 4709882408886284047L;

	/**
	 * 错误码编号
	 */
	private String errorCode;

	/**
	 * 错误信息
	 */
	private String errorMessage;

	/**
	 * 是否需要预警
	 */
	protected boolean alarm;

	public BaseException(){
		super();
	}
	
	public BaseException(String errorCode,String errorMessage){
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BaseException(String errorCode,String errorMessage, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
