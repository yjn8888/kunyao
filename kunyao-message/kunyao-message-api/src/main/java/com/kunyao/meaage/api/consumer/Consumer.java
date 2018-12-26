package com.kunyao.meaage.api.consumer;

public interface Consumer<T> {
    /**
     * 接收消息
     * @return
     */
    T receive(String message);
}
