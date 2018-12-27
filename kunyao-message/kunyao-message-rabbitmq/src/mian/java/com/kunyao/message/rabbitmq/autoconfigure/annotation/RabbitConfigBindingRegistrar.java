package com.kunyao.message.rabbitmq.autoconfigure.annotation;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.amqp.core.AbstractDeclarable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static com.kunyao.util.spring.PropertySourcesUtils.getSubProperties;
import static com.kunyao.util.spring.PropertySourcesUtils.normalizePrefix;
import static org.springframework.beans.factory.support.BeanDefinitionBuilder.rootBeanDefinition;
import static org.springframework.beans.factory.support.BeanDefinitionReaderUtils.registerWithGeneratedName;

public class RabbitConfigBindingRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ConfigurableEnvironment environment;


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(EnableRabbitConfigBinding.class.getName()));

        registerBeanDefinitions(attributes, registry);

    }

    protected void registerBeanDefinitions(AnnotationAttributes attributes, BeanDefinitionRegistry registry) {

        String prefix = environment.resolvePlaceholders(attributes.getString("prefix"));

        Class<? extends AbstractDeclarable> configClass = attributes.getClass("type");

        boolean multiple = attributes.getBoolean("multiple");

        registerRabbitConfigBeans(prefix, configClass, multiple, registry);

    }

    private void registerRabbitConfigBeans(String prefix,
                                          Class<? extends AbstractDeclarable> configClass,
                                          boolean multiple,
                                          BeanDefinitionRegistry registry) {

        Map<String, String> properties = getSubProperties(environment.getPropertySources(), prefix);

        if (CollectionUtils.isEmpty(properties)) {
            if (logger.isDebugEnabled()) {
                logger.debug("There is no property for binding to rabbit config class [" + configClass.getName()
                        + "] within prefix [" + prefix + "]");
            }
            return;
        }

        Set<String> beanNames = multiple ? resolveMultipleBeanNames(properties) :
                Collections.singleton(resolveSingleBeanName(properties, configClass, registry));

        for (String beanName : beanNames) {

            registerRabbitConfigBean(beanName, configClass, registry);

            registerRabbitConfigBindingBeanPostProcessor(prefix, beanName, multiple, registry);

        }

    }

    private void registerRabbitConfigBean(String beanName, Class<? extends AbstractDeclarable> configClass,
                                         BeanDefinitionRegistry registry) {

        BeanDefinitionBuilder builder = rootBeanDefinition(configClass);

        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();

        registry.registerBeanDefinition(beanName, beanDefinition);

        if (logger.isInfoEnabled()) {
            logger.info("The Rabbit config bean definition [name : " + beanName + ", class : " + configClass.getName() +
                    "] has been registered.");
        }

    }

    private void registerRabbitConfigBindingBeanPostProcessor(String prefix, String beanName, boolean multiple,
                                                             BeanDefinitionRegistry registry) {

        Class<?> processorClass = RabbitConfigBindingBeanPostProcessor.class;

        BeanDefinitionBuilder builder = rootBeanDefinition(processorClass);

        String actualPrefix = multiple ? normalizePrefix(prefix) + beanName : prefix;

        builder.addConstructorArgValue(actualPrefix).addConstructorArgValue(beanName);

        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();

        beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

        beanDefinition.setInitMethodName("");

        registerWithGeneratedName(beanDefinition, registry);

        if (logger.isInfoEnabled()) {
            logger.info("The BeanPostProcessor bean definition [" + processorClass.getName()
                    + "] for Rabbit config bean [name : " + beanName + "] has been registered.");
        }

    }

    @Override
    public void setEnvironment(Environment environment) {

        Assert.isInstanceOf(ConfigurableEnvironment.class, environment);

        this.environment = (ConfigurableEnvironment) environment;

    }

    private Set<String> resolveMultipleBeanNames(Map<String, String> properties) {

        Set<String> beanNames = new LinkedHashSet<String>();

        for (String propertyName : properties.keySet()) {

            int index = propertyName.indexOf(".");

            if (index > 0) {

                String beanName = propertyName.substring(0, index);

                beanNames.add(beanName);
            }

        }

        return beanNames;

    }

    private String resolveSingleBeanName(Map<String, String> properties, Class<? extends AbstractDeclarable> configClass,
                                         BeanDefinitionRegistry registry) {

        String beanName = properties.get("id");

        if (!StringUtils.hasText(beanName)) {
            BeanDefinitionBuilder builder = rootBeanDefinition(configClass);
            beanName = BeanDefinitionReaderUtils.generateBeanName(builder.getRawBeanDefinition(), registry);
        }

        return beanName;

    }
}
