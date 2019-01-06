package com.kunyao.message.rabbitmq.support;

import com.kunyao.core.spring.util.SpringContextProvider;
import com.kunyao.logging.trace.LogTraceSerialContext;
import com.kunyao.meaage.api.producer.Producer;
import com.kunyao.meaage.entity.MessageEntity;
import com.kunyao.message.rabbitmq.annotion.EQBinding;
import com.kunyao.message.rabbitmq.autoconfigure.CorrelationDataExtension;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
public abstract class RabbitMQProducer<T> implements Producer, RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

    @Autowired
    protected RabbitTemplate rabbitTemplate;

    @Autowired
    protected SpringContextProvider springContextProvider;

    @Autowired
    protected Jackson2JsonMessageConverter jackson2JsonMessageConverter;

    @PostConstruct
    private void init(){
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        T t = converter(correlationData.getReturnedMessage());
        if(ack){
            log.debug("发送成功的消息：" + correlationData);
            successfulSendoHandle(t);
        }else{
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
        log.error("未正常路由到队列：{message: " + message + ", replyCode: "+ replyCode
                    + ", replyText: "+replyText+", exchange: "+exchange
                    + ", routingKey: "+routingKey+"}");
        T t = converter(message);
        failedSendHandle(t);
    }

    protected abstract void failedSendHandle(T t);

    protected void successfulSendoHandle(T t){

    }


    public void sendMessage(Object t){
        EQBinding eQBinding = this.getClass().getAnnotation(EQBinding.class);
        String beanName = eQBinding.value();
        Binding binding = null;
        if(StringUtils.isNotBlank(beanName)){
            if(springContextProvider.containsBean(beanName)) {
                binding = (Binding) springContextProvider.getBean(beanName);
            }else{
                throw new RuntimeException("不存在指定名称: "+beanName + "的binding！！！");
            }
        }else{
            try {
                binding = (Binding) springContextProvider.getBean(Binding.class);
            }catch (RuntimeException re){
                log.error(re.getMessage(),re);
                throw new RuntimeException("不存在binding或未指定binding！");
            }
        }
        String messageId = LogTraceSerialContext.handleInvokeId(null);
        MessageEntity messageEntity = new MessageEntity(messageId,t);
        Message message = jackson2JsonMessageConverter.toMessage(messageEntity,null);
        CorrelationDataExtension correlationDataExtension = new CorrelationDataExtension();
        correlationDataExtension.setReturnedMessage(message);
        correlationDataExtension.setId(messageId);
        rabbitTemplate.convertAndSend(binding.getExchange(),binding.getRoutingKey(),message,correlationDataExtension);
    }

    @Override
    public void sendMessageWithProperties(Object o, Map properties) {

    }

    private T converter(Message message){
        return ((MessageEntity<T>)jackson2JsonMessageConverter.fromMessage(message)).getData();
    }



}
