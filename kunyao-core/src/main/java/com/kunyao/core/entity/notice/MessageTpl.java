package com.epik.core.entity.notice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MessageTpl implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String tplId;
	private String tplContent;
	private String tplContentBkup;
	private List<MessageTplParam<Object>> params = new ArrayList<MessageTplParam<Object>>();
	public String getTplId() {
		return tplId;
	}
	public void setTplId(String tplId) {
		this.tplId = tplId;
	}
	public String getTplContent() {
		return tplContent;
	}
	public void setTplContent(String tplContent) {
		this.tplContent = tplContent;
	}
	public String getTplContentBkup() {
		return tplContentBkup;
	}
	public void setTplContentBkup(String tplContentBkup) {
		this.tplContentBkup = tplContentBkup;
	}
	public List<MessageTplParam<Object>> getParams() {
		return params;
	}
	public void setParams(List<MessageTplParam<Object>> params) {
		this.params = params;
	}
	
}
