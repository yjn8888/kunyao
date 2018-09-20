package com.epik.core.entity.notice;

import java.io.Serializable;


public class MessageTplParam<T> implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String paramId;
	private Integer paramType;
	private String paramName;
	private String paramLabel;
	private T param;
	private T paramRaw;
	private String format;
	
    public Integer getParamType() {
        return paramType;
    }
    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }
    public String getParamLabel() {
        return paramLabel;
    }
    public void setParamLabel(String paramLabel) {
        this.paramLabel = paramLabel;
    }
    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }
    public T getParamRaw() {
		return paramRaw;
	}
	public void setParamRaw(T paramRaw) {
		this.paramRaw = paramRaw;
	}
	public String getParamId() {
		return paramId;
	}
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	public T getParam() {
		return param;
	}
	public void setParam(T param) {
		this.param = param;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public void makeUp() {
		//todo : customize this part
		this.param = this.paramRaw;
	}
}
