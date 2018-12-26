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

    private Map<String,ExchangeAndQueue> exchangeQueueMap;

    public Map<String, ExchangeAndQueue> getExchangeQueueMap() {
        return exchangeQueueMap;
    }

    public void setExchangeQueueMap(Map<String, ExchangeAndQueue> exchangeQueueMap) {
        this.exchangeQueueMap = exchangeQueueMap;
    }


    private static class ExchangeAndQueue {

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


