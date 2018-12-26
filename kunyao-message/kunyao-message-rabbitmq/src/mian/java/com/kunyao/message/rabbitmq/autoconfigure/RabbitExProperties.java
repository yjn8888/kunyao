package com.kunyao.message.rabbitmq.autoconfigure;

import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(
        prefix = "spring.rabbitmq"
)
public class RabbitExProperties extends RabbitProperties {

    private Map<String,ExchangeQueueBinding> exchangeQueueBindings;

    public Map<String, ExchangeQueueBinding> getExchangeQueueBindings() {
        return exchangeQueueBindings;
    }

    public void setExchangeQueueBindings(Map<String, ExchangeQueueBinding> exchangeQueueBindings) {
        this.exchangeQueueBindings = exchangeQueueBindings;
    }

    private static class ExchangeQueueBinding {

        private CustomExchange exchange;

        private Queue queue;

        private String routingKey;

        public CustomExchange getExchange() {
            return exchange;
        }

        public void setExchange(CustomExchange exchange) {
            this.exchange = exchange;
        }

        public Queue getQueue() {
            return queue;
        }

        public void setQueue(Queue queue) {
            this.queue = queue;
        }

        public String getRoutingKey() {
            return routingKey;
        }

        public void setRoutingKey(String routingKey) {
            this.routingKey = routingKey;
        }
    }
}


