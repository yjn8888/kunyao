package com.kunyao.meaage.monolithic;

import com.kunyao.meaage.MessageEntity;
import com.kunyao.meaage.Producer;

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
