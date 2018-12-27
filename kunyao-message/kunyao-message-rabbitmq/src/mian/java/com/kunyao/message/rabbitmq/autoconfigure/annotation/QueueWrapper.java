package com.kunyao.message.rabbitmq.autoconfigure.annotation;

import org.springframework.amqp.core.AbstractDeclarable;
import org.springframework.amqp.core.Queue;
import org.springframework.lang.Nullable;

import java.util.Map;

public class QueueWrapper extends AbstractDeclarable {

    private Queue queue;
    private String name;
    private boolean durable = true;
    private boolean exclusive;
    private boolean autoDelete;
    private Map<String, Object> arguments;

    private QueueWrapper() {
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

    public final void setMasterLocator(@Nullable String locator) {
        if (locator == null) {
            this.initQueue().getArguments().remove("x-queue-master-locator");
        } else {
            this.initQueue().getArguments().put("x-queue-master-locator", locator);
        }

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
