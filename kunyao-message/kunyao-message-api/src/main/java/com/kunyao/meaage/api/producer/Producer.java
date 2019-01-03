package com.kunyao.meaage.api.producer;

import com.kunyao.meaage.entity.MessageEntity;

public interface Producer<T> {

    void sendMessage(T t);
}
