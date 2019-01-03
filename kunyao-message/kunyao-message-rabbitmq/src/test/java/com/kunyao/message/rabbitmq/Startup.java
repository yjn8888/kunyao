package com.kunyao.message.rabbitmq;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Startup {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext app = new SpringApplicationBuilder(Startup.class).web(WebApplicationType.NONE).run(args);
        RabbitMQProducerTest rabbitMQProducerTest = app.getBean(RabbitMQProducerTest.class);
        int i = 0;
        for(;;){
            //TimeUnit.MILLISECONDS.sleep(10);
            rabbitMQProducerTest.sendMessage(new TestMessage(i,"消息"+i,i+10,"北京"));
            i++;
            if(i==100){
                break;
            }
        }

    }
}
