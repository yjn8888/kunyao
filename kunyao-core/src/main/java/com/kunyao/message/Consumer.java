package com.kunyao.message;

public interface Consumer<T> {
    /**
     * 接收消息并处理
     * @return boolean
     */
    boolean receiveMessage(MessageEntity<T> messageEntity);
}
