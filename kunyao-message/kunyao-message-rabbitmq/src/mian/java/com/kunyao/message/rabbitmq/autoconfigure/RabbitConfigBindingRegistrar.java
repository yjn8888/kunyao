package com.kunyao.message.rabbitmq.autoconfigure;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

public class RabbitConfigBindingRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ConfigurableEnvironment environment;

    @Override
    public void setEnvironment(Environment environment) {

    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

    }
}
