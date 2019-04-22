package com.kunyao.message;

public interface Consumer<T> {
    /**
     * 接收消息
     * @return
     */
    boolean receiveMessage(MessageEntity<T> messageEntity);
}
