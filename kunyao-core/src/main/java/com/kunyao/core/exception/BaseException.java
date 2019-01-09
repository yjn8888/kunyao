package com.kunyao.core.exception;



/* *
 * @author 
 * @version 1.0
 */
public class BaseException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4709882408886284047L;

	private String errorCode;
	
	private String errorMessage;

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

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
