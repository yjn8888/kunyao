package com.kunyao.core.entity.invoke;


/**
 * 此类描述的是：BaseInvokeResult
 * @author: zhangrongbinbj@hanhua.com
 * @since : 2015年8月6日
 */
public class BaseInvokeResult<E> {
	/**
	 * 1000 System failed 0000 success
	 */
	private String resultCode = "1000";
	
	/**
	 * 结果信息
	 */
	private String resultMessage = "System Error, please try again later";
	
	private String invokeId;

	public String getInvokeId() {
		return invokeId;
	}

	public void setInvokeId(String invokeId) {
		this.invokeId = invokeId;
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
	}
}
