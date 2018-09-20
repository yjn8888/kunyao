package com.epik.core.entity.notice;

import java.io.Serializable;

public class Message implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String msgId;
	private String code;
	private String content;
	private Integer messageType;
	private MessageTpl messageTpl;

	public Integer getMessageType() {
        return messageType;
    }
    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }
    public String getMsgId() {
        return msgId;
    }
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MessageTpl getMessageTpl() {
		return messageTpl;
	}
	public void setMessageTpl(MessageTpl messageTpl) {
		this.messageTpl = messageTpl;
	}
	
	
}
