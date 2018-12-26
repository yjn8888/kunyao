package com.kunyao.message.rabbitmq.support;

import com.kunyao.meaage.api.consumer.Consumer;
import com.kunyao.meaage.entity.MessageEntity;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.messaging.handler.annotation.Payload;

public abstract class RabbitMQConsumer implements Consumer<MessageEntity> {

    @RabbitHandler
    public MessageEntity receive(@Payload Object merchant) {
        return null;
    }
}
