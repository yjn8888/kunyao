package com.kunyao.message.rabbitmq;


import com.kunyao.message.rabbitmq.annotation.EQBinding;
import com.kunyao.message.rabbitmq.support.RabbitMQProducer;
import org.springframework.stereotype.Component;

@Component
@EQBinding("binding1")
public class RabbitMQProducerTest extends RabbitMQProducer<TestMessage> {

    @Override
    protected void failedSendHandle(TestMessage testMessage) {

    }
}
