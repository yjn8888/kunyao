package com.kunyao.message.rabbitmq.autoconfigure;

import com.kunyao.core.spring.annotation.EnableConfig;
import com.kunyao.message.rabbitmq.support.ConfirmCallBackListener;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
@ConditionalOnClass({RabbitTemplate.class, Channel.class})
public class RabbitAutoConfiguration {

    @EnableConfig(multipleClass = RabbitConfigConfigration.Multiple.class)
    protected static class MultipleRabbitConfigConfiguration {
    }

    @EnableConfig(singleClass = RabbitConfigConfigration.Single.class, multiple=false)
    protected static class SingleRabbitConfigConfiguration{
    }

    @Bean
    @ConditionalOnMissingBean(Queue.class)
    public Queue defaultQueue(){
        return new QueueWrapper("defaultQueue");
    }

    @Bean
    @ConditionalOnMissingBean(Exchange.class)
    public Exchange defaultExchange(){
        return new ExchangeWrapper("defaultExchange");
    }

    @Bean
    @ConditionalOnMissingBean(Binding.class)
    public Binding defaultBinding(@Qualifier("defaultQueue") Queue queue, @Qualifier("defaultExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("defaultRoutingKey").noargs();
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(final CachingConnectionFactory connectionFactory,
                                         @Qualifier("jackson2JsonMessageConverter") Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        connectionFactory.setPublisherConfirms(true);
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        rabbitTemplate.setConfirmCallback(new ConfirmCallBackListener());
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(@Qualifier("rabbitTemplate")RabbitTemplate rabbitTemplate){
        return new RabbitAdmin(rabbitTemplate);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(final CachingConnectionFactory connectionFactory,
                                                                               @Qualifier("jackson2JsonMessageConverter") Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter);
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setAutoStartup(true);
        return factory;
    }
}
