package com.kunyao.meaage.api.consumer;

import com.kunyao.meaage.entity.MessageEntity;

public interface Consumer<T> {
    /**
     * 接收消息
     * @return
     */
    void receiveMessage(MessageEntity<T> messageEntity);
}
