package com.kunyao.message.rabbitmq;


import com.kunyao.message.rabbitmq.annotation.Binding;
import com.kunyao.message.rabbitmq.support.RabbitMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Binding("binding1")
public class RabbitMQProducerTest extends RabbitMQProducer<TestMessage> {

    @Override
    protected void failedSendHandle(TestMessage testMessage) {
        log.error("message={}",testMessage.toString());
    }
}
