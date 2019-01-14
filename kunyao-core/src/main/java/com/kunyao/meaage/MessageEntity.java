package com.kunyao.meaage;

import com.kunyao.core.entity.base.Entity;

public class MessageEntity<T> implements Entity {

    private static final long serialVersionUID = -2708504431483704392L;
    /**
     * 用于分布式追踪
     */
    private String mesageId;

    private T data;

    public MessageEntity(){

    }

    public MessageEntity(String mesageId, T data) {
        this.mesageId = mesageId;
        this.data = data;
    }

    public String getMesageId() {
        return mesageId;
    }

    public void setMesageId(String mesageId) {
        this.mesageId = mesageId;
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
                "mesageId='" + mesageId + '\'' +
                ", data=" + data +
                '}';
    }
}