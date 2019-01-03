package com.kunyao.message.rabbitmq;

import com.kunyao.message.rabbitmq.support.RabbitMQConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RabbitListener(queues = "${spring.rabbitmq.queues.queue1.name}")
public class RabbitMQConsumerTest extends RabbitMQConsumer<TestMessage> {

    private boolean isStart;

    @Override
    protected void process(TestMessage testMesage) {
        if(!isStart){
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("当前线程："+ Thread.currentThread().getId());
        System.out.println(testMesage);
    }
}
