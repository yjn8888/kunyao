package com.kunyao.message.rabbitmq.autoconfigure;

import com.kunyao.message.rabbitmq.autoconfigure.annotation.EnableRabbitConfig;
import com.kunyao.message.rabbitmq.autoconfigure.properties.RelaxedRabbitConfigBinder;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(@Qualifier("rabbitTemplate")RabbitTemplate rabbitTemplate){
        return new RabbitAdmin(rabbitTemplate);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setAutoStartup(true);
        return factory;
    }


}
