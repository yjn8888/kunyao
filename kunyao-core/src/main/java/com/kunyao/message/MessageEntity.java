package com.kunyao.message;

import com.kunyao.core.entity.base.Entity;

public class MessageEntity<T> implements Entity {

    private static final long serialVersionUID = -2708504431483704392L;
    /**
     * 用于分布式追踪
     */
    private String messageId;

    private T data;

    public MessageEntity(){

    }

    public MessageEntity(String messageId, T data) {
        this.messageId = messageId;
        this.data = data;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "messageId='" + messageId + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public String getTraceId() {
        return messageId;
    }
}
