package com.kunyao.message.rabbitmq.autoconfigure.annotation;

import com.kunyao.message.rabbitmq.autoconfigure.properties.DefaultRabbitConfigBinder;
import com.kunyao.message.rabbitmq.autoconfigure.properties.RabbitConfigBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AbstractDeclarable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

/**
 * Rabbit Config Binding {@link BeanPostProcessor}
 *
 * @see EnableRabbitConfigBinding
 * @see RabbitConfigBindingRegistrar
 */

public class RabbitConfigBindingBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware, InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * The prefix of Configuration Properties
     */
    private final String prefix;

    /**
     * Binding Bean Name
     */
    private final String beanName;

    private RabbitConfigBinder rabbitConfigBinder;

    private ApplicationContext applicationContext;

    private boolean ignoreUnknownFields = true;

    private boolean ignoreInvalidFields = true;

    /**
     * @param prefix   the prefix of Configuration Properties
     * @param beanName the binding Bean Name
     */
    public RabbitConfigBindingBeanPostProcessor(String prefix, String beanName) {
        Assert.notNull(prefix, "The prefix of Configuration Properties must not be null");
        Assert.notNull(beanName, "The name of bean must not be null");
        this.prefix = prefix;
        this.beanName = beanName;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (beanName != null && beanName.equals(this.beanName) && bean instanceof AbstractDeclarable) {

            AbstractDeclarable RabbitConfig = (AbstractDeclarable) bean;

            rabbitConfigBinder.bind(prefix, RabbitConfig);

            if (logger.isInfoEnabled()) {
                logger.info("The properties of bean [name : " + beanName + "] have been binding by prefix of " +
                        "configuration properties : " + prefix);
            }
        }

        return bean;

    }

    public boolean isIgnoreUnknownFields() {
        return ignoreUnknownFields;
    }

    public void setIgnoreUnknownFields(boolean ignoreUnknownFields) {
        this.ignoreUnknownFields = ignoreUnknownFields;
    }

    public boolean isIgnoreInvalidFields() {
        return ignoreInvalidFields;
    }

    public void setIgnoreInvalidFields(boolean ignoreInvalidFields) {
        this.ignoreInvalidFields = ignoreInvalidFields;
    }

    public RabbitConfigBinder getRabbitConfigBinder() {
        return rabbitConfigBinder;
    }

    public void setRabbitConfigBinder(RabbitConfigBinder rabbitConfigBinder) {
        this.rabbitConfigBinder = rabbitConfigBinder;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if (rabbitConfigBinder == null) {
            try {
                rabbitConfigBinder = applicationContext.getBean(RabbitConfigBinder.class);
            } catch (BeansException ignored) {
                if (logger.isDebugEnabled()) {
                    logger.debug("RabbitConfigBinder Bean can't be found in ApplicationContext.");
                }
                // Use Default implementation
                rabbitConfigBinder = createRabbitConfigBinder(applicationContext.getEnvironment());
            }
        }

        rabbitConfigBinder.setIgnoreUnknownFields(ignoreUnknownFields);
        rabbitConfigBinder.setIgnoreInvalidFields(ignoreInvalidFields);

    }

    /**
     * Create {@link RabbitConfigBinder} instance.
     *
     * @param environment
     * @return {@link DefaultRabbitConfigBinder}
     */
    protected RabbitConfigBinder createRabbitConfigBinder(Environment environment) {
        DefaultRabbitConfigBinder defaultRabbitConfigBinder = new DefaultRabbitConfigBinder();
        defaultRabbitConfigBinder.setEnvironment(environment);
        return defaultRabbitConfigBinder;
    }

}
