package com.kunyao.core.spring.context;

import com.kunyao.core.spring.annotation.EnableConfigBindings;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

public class ConfigBindingsRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private ConfigurableEnvironment environment;

    @Override
    public void setEnvironment(Environment environment) {
        Assert.isInstanceOf(ConfigurableEnvironment.class, environment);

        this.environment = (ConfigurableEnvironment) environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                annotationMetadata.getAnnotationAttributes(EnableConfigBindings.class.getName()));

        AnnotationAttributes[] annotationAttributes = attributes.getAnnotationArray("value");

        ConfigBindingRegistrar registrar = new ConfigBindingRegistrar();
        registrar.setEnvironment(environment);

        for (AnnotationAttributes element : annotationAttributes) {

            registrar.registerBeanDefinitions(element, beanDefinitionRegistry);

        }
    }
}
