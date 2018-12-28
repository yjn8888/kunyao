package com.kunyao.message.rabbitmq.support;

import com.kunyao.message.rabbitmq.autoconfigure.annotation.BindingWrapper;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Test {

    public static void main(String[] args){
        ApplicationContext app = new SpringApplicationBuilder(Test.class).web(WebApplicationType.NONE).run(args);
        RabbitTemplate amqpTemplate = (RabbitTemplate)app.getBean("rabbitTemplate");
        System.out.println(app.getBean("exchange1").getClass());
        Exchange exchange1 = (Exchange)app.getBean("exchange1");
        System.out.println(exchange1);
        String routingKey1 = ((BindingWrapper)app.getBean("binding1")).getRoutingKey();
        Queue queue1 = (Queue)app.getBean("queue1");

        Exchange exchange2 = (Exchange)app.getBean("exchange2");
        System.out.println(exchange2);
        String routingKey2 = ((BindingWrapper)app.getBean("binding2")).getRoutingKey();
        amqpTemplate.convertAndSend(exchange2.getName(),routingKey2,"aaaaaaaaaaaaa");
//        BindingBuilder.bind(queue1).to(exchange1).with(routingKey1);
        amqpTemplate.convertAndSend(exchange1.getName(),routingKey1,"uuuuuuuuuuuu");
    }
}
