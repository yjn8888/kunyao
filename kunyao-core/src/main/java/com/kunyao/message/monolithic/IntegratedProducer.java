package com.kunyao.message.monolithic;

import com.kunyao.message.MessageEntity;
import com.kunyao.message.Producer;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class IntegratedProducer<T> implements Producer {

    private BlockingQueue<MessageEntity<IntegratedMessage<T>>> blockingQueue;

    @Override
    public void sendMessage(Object o) {

    }

    @Override
    public void sendMessageWithProperties(Object o, Map properties) {

    }
}
