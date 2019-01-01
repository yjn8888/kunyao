package com.kunyao.message.rabbitmq.support;

import com.kunyao.message.rabbitmq.autoconfigure.BindingWrapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.UUID;

@SpringBootApplication(scanBasePackages = "com.kunyao.message.rabbitmq.autoconfigure")
public class Test {

    public static void main(String[] args){
        ApplicationContext app = new SpringApplicationBuilder(Test.class).web(WebApplicationType.NONE).run(args);
        RabbitTemplate amqpTemplate = (RabbitTemplate)app.getBean("rabbitTemplate");
//        System.out.println(app.getBean("exchange1").getClass());
//        Exchange exchange1 = (Exchange)app.getBean("exchange1");
//        System.out.println(exchange1);
//        String routingKey1 = ((BindingWrapper)app.getBean("binding1")).getRoutingKey();
//        Queue queue1 = (Queue)app.getBean("queue1");
//
//        Exchange exchange2 = (Exchange)app.getBean("exchange2");
//        System.out.println(exchange2);
//        String routingKey2 = ((BindingWrapper)app.getBean("binding2")).getRoutingKey();
//        amqpTemplate.convertAndSend(exchange2.getName(),routingKey2,"aaaaaaaaaaaaa");
//        BindingBuilder.bind(queue1).to(exchange1).with(routingKey1);

//        System.out.println(app.getBean("exchange1").getClass());
        Exchange exchange1 = (Exchange)app.getBean(Exchange.class);
        System.out.println(exchange1);
        String routingKey1 = ((Binding)app.getBean(Binding.class)).getRoutingKey();
        Queue queue1 = (Queue)app.getBean(Queue.class);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        int i = 0;
        System.out.println("当前线程："+Thread.currentThread().getId());
        while (true) {
            if(i==100){
                break;
            }
            System.out.println("当前线程："+Thread.currentThread().getId());
            correlationData = new CorrelationData(UUID.randomUUID().toString());
            amqpTemplate.convertAndSend(exchange1.getName(),routingKey1,"222222222",correlationData);
            i++;
        }
//        amqpTemplate.convertAndSend(exchange1.getName(),routingKey1,"222222222",correlationData);
//        correlationData = new CorrelationData(UUID.randomUUID().toString());
//        //correlationData.s
//        amqpTemplate.convertAndSend(exchange1.getName(),routingKey1,"222222222",correlationData);
//        correlationData = new CorrelationData(UUID.randomUUID().toString());
//        //correlationData.s
//        amqpTemplate.convertAndSend(exchange1.getName(),routingKey1,"222222222",correlationData);
        correlationData = new CorrelationData(UUID.randomUUID().toString());
//        amqpTemplate.convertAndSend(exchange1.getName()+1,routingKey1,"222222222",correlationData);
    }
}
