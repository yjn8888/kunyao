package com.kunyao.message.rabbitmq.autoconfigure;

import org.springframework.amqp.core.Queue;

import java.util.Map;

public class QueueWrapper extends Queue {

    private Queue queue;
    private String name;
    private boolean durable = true;
    private boolean exclusive;
    private boolean autoDelete;
    private Map<String, Object> arguments;

    public QueueWrapper() {
        super("");
    }

    public QueueWrapper(String name) {
        super(name);
        this.name = name;
    }

    public boolean isDurable() {
        return this.initQueue().isDurable();
    }

    public boolean isExclusive() {
        return this.initQueue().isExclusive();
    }

    public boolean isAutoDelete() {
        return this.initQueue().isAutoDelete();
    }

    public Map<String, Object> getArguments() {
        return this.initQueue().getArguments();
    }

    public void setActualName(String actualName) {
        this.initQueue().setActualName(actualName);
    }

    public String getActualName() {
        return this.initQueue().getActualName();
    }

    public String toString() {
        return this.initQueue().toString();
    }

    public String getName() {
        return this.initQueue().getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDurable(boolean durable) {
        this.durable = durable;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public void setAutoDelete(boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    public void setArguments(Map<String, Object> arguments) {
        this.arguments = arguments;
    }

    private Queue initQueue(){
        if(this.queue==null){
            this.queue = new Queue(name, durable, exclusive, autoDelete, arguments);
        }
        return this.queue;
    }
}
