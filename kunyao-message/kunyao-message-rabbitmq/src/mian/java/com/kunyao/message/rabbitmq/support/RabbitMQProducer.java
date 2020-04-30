package com.kunyao.message.rabbitmq.support;

import com.kunyao.core.exception.SysException;
import com.kunyao.core.spring.util.SpringContextProvider;
import com.kunyao.logging.trace.LogTraceSerialContext;
import com.kunyao.logging.trace.annotation.LogTrace;
import com.kunyao.message.MessageEntity;
import com.kunyao.message.Producer;
import com.kunyao.message.rabbitmq.annotation.Binding;
import com.kunyao.message.rabbitmq.autoconfigure.CorrelationDataExtension;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
public abstract class RabbitMQProducer<T> implements Producer, RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    protected RabbitTemplate rabbitTemplate;

    @Autowired
    protected SpringContextProvider springContextProvider;

    @Autowired
    protected Jackson2JsonMessageConverter jackson2JsonMessageConverter;

    @PostConstruct
    private void init() {
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        T t = converter(correlationData.getReturnedMessage());
        if (ack) {
            log.debug("发送成功的消息：" + correlationData);
            successfulSendHandle(t);
        } else {
            log.error("发送失败的消息：" + correlationData);
            log.error("消息发送失败原因：" + cause);
            failedSendHandle(t);
        }

    }

    @Override
    public void returnedMessage(Message message, int replyCode,
                                String replyText,
                                String exchange,
                                String routingKey) {
        log.error("未正常路由到队列：[message={},replyCode={},replyText={},exchange={},routingKey={}]", message, replyCode
                , replyText, exchange, routingKey);
        T t = converter(message);
        failedSendHandle(t);
    }

    protected abstract void failedSendHandle(T t);

    protected void successfulSendHandle(T t) {

    }


    @Override
    @LogTrace
    public void sendMessage(Object t) {
        Binding eQBinding = this.getClass().getAnnotation(Binding.class);
        String beanName = eQBinding.value();
        org.springframework.amqp.core.Binding binding = null;
        if (StringUtils.isNotBlank(beanName)) {
            if (SpringContextProvider.containsBean(beanName)) {
                binding = (org.springframework.amqp.core.Binding) SpringContextProvider.getBean(beanName);
            } else {
                throw new RuntimeException("不存在指定名称: " + beanName + "的binding！！！");
            }
        } else {
            try {
                binding = (org.springframework.amqp.core.Binding) SpringContextProvider.getBean(org.springframework.amqp.core.Binding.class);
            } catch (RuntimeException re) {
                log.error(re.getMessage(), re);
                throw new SysException("", "不存在binding或未指定binding！", re);
            }
        }
        String messageId = LogTraceSerialContext.handleInvokeId(null);
        MessageEntity messageEntity = new MessageEntity(messageId, t);
        Message message = jackson2JsonMessageConverter.toMessage(messageEntity, null);
        CorrelationDataExtension correlationDataExtension = new CorrelationDataExtension();
        correlationDataExtension.setReturnedMessage(message);
        correlationDataExtension.setId(messageId);
        rabbitTemplate.convertAndSend(binding.getExchange(), binding.getRoutingKey(), message, correlationDataExtension);
    }

    @Override
    public void sendMessageWithProperties(Object o, Map properties) {

    }

    private T converter(Message message) {
        return ((MessageEntity<T>) jackson2JsonMessageConverter.fromMessage(message)).getData();
    }


}
