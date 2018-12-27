package com.kunyao.message.rabbitmq.support;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Test {

    public static void main(String[] args){
        ApplicationContext app = new SpringApplicationBuilder(Test.class).web(WebApplicationType.NONE).run(args);
        Object a = app.getBean("testqueue");
        System.out.println(a);
        a = app.getBean("testqueue1");
        System.out.println(a);
    }
}
