package com.kunyao.meaage.monolithic;

import com.kunyao.meaage.Consumer;
import com.kunyao.meaage.MessageEntity;

import java.util.concurrent.BlockingQueue;

public class IntegratedConsumer<T> implements Consumer {

    private BlockingQueue<MessageEntity<IntegratedMessage<T>>> blockingQueue;

    @Override
    public boolean receiveMessage(MessageEntity messageEntity) {
        return false;
    }
}
