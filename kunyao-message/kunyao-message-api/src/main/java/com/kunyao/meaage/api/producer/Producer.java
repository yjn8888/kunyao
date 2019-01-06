package com.kunyao.meaage.api.producer;

import com.kunyao.meaage.entity.MessageEntity;

import java.util.Map;

public interface Producer<T> {

    void sendMessage(T t);

    void sendMessageWithProperties(T t, Map<String,Object> properties);
}
