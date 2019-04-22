package com.kunyao.message.monolithic;

import com.kunyao.message.Consumer;
import com.kunyao.message.MessageEntity;

import java.util.concurrent.BlockingQueue;

public class IntegratedConsumer<T> implements Consumer {

    private BlockingQueue<MessageEntity<IntegratedMessage<T>>> blockingQueue;

    @Override
    public boolean receiveMessage(MessageEntity messageEntity) {
        return false;
    }
}
