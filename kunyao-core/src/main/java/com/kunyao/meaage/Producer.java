package com.kunyao.meaage;

import java.util.Map;

public interface Producer<T> {

    void sendMessage(T t);

    void sendMessageWithProperties(T t, Map<String, Object> properties);
}
