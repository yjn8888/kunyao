package com.kunyao.core.exception;


import lombok.Data;

/* *
 * @author 
 * @version 1.0
 */
@Data
public abstract class BaseException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4709882408886284047L;

	public static final String DEFAULT_SYS_ERROR_MESSAGE = "System Error! Please try again OR Contact your administrator to solve .";

	public static final String DEFAULT_BIZ_ERROR_CODE = "BIZ-00001";

	public static final String DEFAULT_SYS_ERROR_CODE = "SYS-00001";

	private String errorCode;
	
	private String errorMessage;

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
