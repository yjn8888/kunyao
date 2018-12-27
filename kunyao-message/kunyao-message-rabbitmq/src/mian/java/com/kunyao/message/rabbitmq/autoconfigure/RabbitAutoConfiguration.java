package com.kunyao.message.rabbitmq.autoconfigure;

import com.kunyao.message.rabbitmq.autoconfigure.annotation.EnableRabbitConfig;
import com.kunyao.message.rabbitmq.autoconfigure.properties.RelaxedRabbitConfigBinder;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
@ConditionalOnClass({RabbitTemplate.class, Channel.class})
//@EnableConfigurationProperties({RabbitExProperties.class})
public class RabbitAutoConfiguration {

    @EnableRabbitConfig(multiple = true)
    protected static class MultipleDubboConfigConfiguration {
    }

    @ConditionalOnClass(Binder.class)
    @Bean
    @Scope(scopeName = SCOPE_PROTOTYPE)
    public RelaxedRabbitConfigBinder relaxedDubboConfigBinder() {
        return new RelaxedRabbitConfigBinder();
    }
}
