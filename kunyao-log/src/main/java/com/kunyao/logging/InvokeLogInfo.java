package com.kunyao.logging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kunyao.core.entity.access.BaseAccessInfo;



/**
 * 此类描述的是：InvokeLog值对象
 * @author: zhangrongbinbj@hanhua.com
 * @since : 2015年8月6日
 */
public class InvokeLogInfo implements Serializable{
	
	private static final long serialVersionUID = -3057848441033393899L;
	private Date startDate;
	private Date endDate;
	private Long startTime;
	private Long endTime;
	private BaseAccessInfo accessInfo;
	private String invokeId;
	private String method;
	private String moduleName;
	private String operation;
	private List<String> logContentList;
	private Long invokeCount;
	private String host;
	private Object returnValue;
	private Long timeCosted;
	private String result;
	private Object exceptionMessage;
	private List<Object> params = new ArrayList<Object>();
	private List<InvokeLogInfo> subInvokeList = new ArrayList<InvokeLogInfo>();
	
	@com.fasterxml.jackson.annotation.JsonIgnore
	@org.codehaus.jackson.annotate.JsonIgnore
	private InvokeLogInfo currentParent = null;
	
	public List<String> getLogContentList() {
        return logContentList;
    }
    public void setLogContentList(List<String> logContentList) {
        this.logContentList = logContentList;
    }
    public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public List<InvokeLogInfo> getSubInvokeList() {
		return subInvokeList;
	}
	public void setSubInvokeList(List<InvokeLogInfo> subInvokeList) {
		this.subInvokeList = subInvokeList;
	}
	
	public InvokeLogInfo getCurrentParent() {
		return currentParent;
	}
	public void setCurrentParent(InvokeLogInfo currentParent) {
		this.currentParent = currentParent;
	}
	public List<Object> getParams() {
		return params;
	}
	public void setParams(List<Object> params) {
		this.params = params;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Object getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(Object exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public BaseAccessInfo getAccessInfo() {
		return accessInfo;
	}
	public void setAccessInfo(BaseAccessInfo accessInfo) {
		this.accessInfo = accessInfo;
	}
	public String getInvokeId() {
		return invokeId;
	}
	public void setInvokeId(String invokeId) {
		this.invokeId = invokeId;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Long getInvokeCount() {
		return invokeCount;
	}
	public void setInvokeCount(Long invokeCount) {
		this.invokeCount = invokeCount;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Object getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(Object returnValue) {
		this.returnValue = returnValue;
	}
	public Long getTimeCosted() {
		return timeCosted;
	}
	public void setTimeCosted(Long timeCosted) {
		this.timeCosted = timeCosted;
	}
}
