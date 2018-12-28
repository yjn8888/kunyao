package com.kunyao.message.rabbitmq.autoconfigure.annotation;

import org.springframework.amqp.core.AbstractDeclarable;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Exchange;

import java.util.HashMap;
import java.util.Map;

public class ExchangeWrapper extends AbstractDeclarable  implements Exchange {

    private Exchange exchange;

    public void setName(String name) {
        this.name = name;
    }

    public void setDurable(boolean durable) {
        this.durable = durable;
    }

    public void setAutoDelete(boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    public void setArguments(Map<String, Object> arguments) {
        this.arguments = arguments;
    }

    private String name = "";
    private boolean durable = true;
    private boolean autoDelete;
    private Map<String, Object> arguments;
    private volatile boolean delayed;
    private boolean internal;
    private String type = "direct";

    public ExchangeWrapper(){

    }

    public ExchangeWrapper(String name) {
        this(name, true, false);
    }

    public ExchangeWrapper(String name, boolean durable, boolean autoDelete) {
        this(name, durable, autoDelete, (Map)null);
    }

    public ExchangeWrapper(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
        this.name = name;
        this.durable = durable;
        this.autoDelete = autoDelete;
        if (arguments != null) {
            this.arguments = arguments;
        } else {
            this.arguments = new HashMap();
        }

    }

    public String getType(){
        return this.initExchange().getType();
    }

    public String getName() {
        return this.initExchange().getName();
    }

    public boolean isDurable() {
        return this.initExchange().isDurable();
    }

    public boolean isAutoDelete() {
        return this.initExchange().isAutoDelete();
    }

    protected synchronized void addArgument(String argName, Object argValue) {
        this.arguments.put(argName, argValue);
    }

    public Map<String, Object> getArguments() {
        return this.initExchange().getArguments();
    }

    public boolean isDelayed() {
        return this.initExchange().isDelayed();
    }

    public void setDelayed(boolean delayed) {
        this.delayed = delayed;
    }

    public boolean isInternal() {
        return this.initExchange().isInternal();
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    public String toString() {
        return this.initExchange().toString();
    }

    private Exchange initExchange(){
        if(this.exchange==null){
            this.exchange = new CustomExchange(name,type, durable, autoDelete, arguments);
        }
        return this.exchange;
    }

    public void setType(String type) {
        this.type = type;
    }
}
