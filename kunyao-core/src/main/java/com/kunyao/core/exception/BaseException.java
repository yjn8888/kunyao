package com.kunyao.core.exception;


/**
 * Copyright 2012-2013 hanhua.com
 * 
 * ID: $Id: VapBaseException.java 2013-3-14 11:36 Liuwenlong $
 * Revision: $Rev: 21 $
 * Created: 2013-3-14
 * Last modified: $Date: 2013-3-14 11:36 +0800 $
 * Last modified by: $Author: Liuwenlong $
 * 
 */


/**
 * @category 系统级异常（开发人员误操作或系统本身产生的异常，如数据库连接失败，空指针错误）的基类，是一个不受控异常基类。
 *           可不用捕捉或传throws方式传递下去。
 *           <p/>
 *           * 示例如下：<br>
 * 
 *           <pre>
 * 1)抛出错误(Service)
 * public void getConnection() throws VapBaseException {
 *      ...
 *      throw new VapBaseException(&quot;e_login_database_notconnect&quot;);
 *      ...
 *      throw new VapBaseException(&quot;e_login_connect_failed&quot;,new Object[]{&quot;admin&quot;,&quot;1234&quot;});
 *      ...
 * }
 * 2)捕捉错误(Action)
 * &lt;p/&gt;
 * public login() {
 *      try {
 *           ...
 *      } catch(VapBaseException ex) {
 *          //如果只抛出一种错误，可不加if 条件判断
 *           if (ex.getExceptionKey().equals(&quot;e_login_database_notconnect&quot;)) {
 *               ...
 *               this.hintErrorMessage(ex.getMessage());
 *           }
 * &lt;p/&gt;
 *           if (ex.getExceptionKey().equals(&quot;e_login_connect_failed&quot;)) {
 *               ...
 *               this.hintErrorMessage(ex.getMessage());
 *           }
 *      }
 * }
 * </pre>
 * 
 * @author Liuwenlong
 * @version 1.0 {@code RuntimeException}
 */
public class BaseException extends RuntimeException {

	
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
