package com.kunyao.core.spring.context;

import com.kunyao.core.spring.annotation.EnableConfigBinding;
import lombok.extern.slf4j.Slf4j;
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

import static com.kunyao.core.spring.util.PropertySourcesUtils.getSubProperties;
import static com.kunyao.core.spring.util.PropertySourcesUtils.normalizePrefix;
import static org.springframework.beans.factory.support.BeanDefinitionBuilder.rootBeanDefinition;
import static org.springframework.beans.factory.support.BeanDefinitionReaderUtils.registerWithGeneratedName;

@Slf4j
public class ConfigBindingRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private ConfigurableEnvironment environment;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(EnableConfigBinding.class.getName()));

        registerBeanDefinitions(attributes, registry);

    }

    protected void registerBeanDefinitions(AnnotationAttributes attributes, BeanDefinitionRegistry registry) {

        String prefix = environment.resolvePlaceholders(attributes.getString("prefix"));

        Class<?> configClass = attributes.getClass("type");

        boolean multiple = attributes.getBoolean("multiple");

        registerRabbitConfigBeans(prefix, configClass, multiple, registry);

    }

    private void registerRabbitConfigBeans(String prefix,
                                          Class<?> configClass,
                                          boolean multiple,
                                          BeanDefinitionRegistry registry) {

        Map<String, String> properties = getSubProperties(environment.getPropertySources(), prefix);

        if (CollectionUtils.isEmpty(properties)) {
            if (log.isDebugEnabled()) {
                log.debug("There is no property for binding to autoconfigure class [" + configClass.getName()
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

    private void registerRabbitConfigBean(String beanName, Class<?> configClass,
                                         BeanDefinitionRegistry registry) {

        BeanDefinitionBuilder builder = rootBeanDefinition(configClass);

        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();

        registry.registerBeanDefinition(beanName, beanDefinition);

        if (log.isInfoEnabled()) {
            log.info("The autoconfigure bean definition [name : " + beanName + ", class : " + configClass.getName() +
                    "] has been registered.");
        }

    }

    private void registerRabbitConfigBindingBeanPostProcessor(String prefix, String beanName, boolean multiple,
                                                             BeanDefinitionRegistry registry) {

        Class<?> processorClass = ConfigBindingBeanPostProcessor.class;

        BeanDefinitionBuilder builder = rootBeanDefinition(processorClass);

        String actualPrefix = multiple ? normalizePrefix(prefix) + beanName : prefix;

        builder.addConstructorArgValue(actualPrefix).addConstructorArgValue(beanName);

        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();

        beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

        beanDefinition.setInitMethodName("");

        registerWithGeneratedName(beanDefinition, registry);

        if (log.isInfoEnabled()) {
            log.info("The BeanPostProcessor bean definition [" + processorClass.getName()
                    + "] for autoconfigure bean [name : " + beanName + "] has been registered.");
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

    private String resolveSingleBeanName(Map<String, String> properties, Class<?> configClass,
                                         BeanDefinitionRegistry registry) {

        String beanName = properties.get("id");

        if (!StringUtils.hasText(beanName)) {
            BeanDefinitionBuilder builder = rootBeanDefinition(configClass);
            beanName = BeanDefinitionReaderUtils.generateBeanName(builder.getRawBeanDefinition(), registry);
        }

        return beanName;

    }
}
