package com.kunyao.meaage.api.consumer;

import com.kunyao.meaage.entity.MessageEntity;

public interface Consumer<T> {
    /**
     * 接收消息
     * @return
     */
    boolean receiveMessage(MessageEntity<T> messageEntity);
}
