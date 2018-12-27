package com.kunyao.message.rabbitmq.autoconfigure.annotation;

import org.springframework.amqp.core.AbstractDeclarable;
import org.springframework.amqp.core.Binding;

import java.util.Map;

public class BindingWrapper extends AbstractDeclarable {

    private Binding binding;
    private String destination;
    private String exchange;
    private String routingKey;
    private Map<String, Object> arguments;
    private Binding.DestinationType destinationType;


    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public void setArguments(Map<String, Object> arguments) {
        this.arguments = arguments;
    }

    public void setDestinationType(Binding.DestinationType destinationType) {
        this.destinationType = destinationType;
    }


    public BindingWrapper(){}

    public BindingWrapper(String destination, Binding.DestinationType destinationType, String exchange, String routingKey, Map<String, Object> arguments) {
        this.destination = destination;
        this.destinationType = destinationType;
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.arguments = arguments;
    }

    public String getDestination() {
        return this.initBinding().getDestination();
    }

    public Binding.DestinationType getDestinationType() {
        return this.initBinding().getDestinationType();
    }

    public String getExchange() {
        return this.initBinding().getExchange();
    }

    public String getRoutingKey() {
        return this.initBinding().getRoutingKey();
    }

    public Map<String, Object> getArguments() {
        return this.initBinding().getArguments();
    }

    public boolean isDestinationQueue() {
        return this.initBinding().isDestinationQueue();
    }

    public String toString() {
        return this.initBinding().toString();
    }

    private Binding initBinding(){
        if(this.binding==null){
            this.binding = new Binding(destination, destinationType, exchange, routingKey, arguments);
        }
        return this.binding;
    }

}
