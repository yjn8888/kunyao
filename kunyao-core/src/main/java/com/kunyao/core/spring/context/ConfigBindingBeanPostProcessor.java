package com.kunyao.core.spring.context;

import com.kunyao.core.spring.annotation.EnableConfigBinding;
import com.kunyao.core.spring.properties.ConfigBinder;
import com.kunyao.core.spring.properties.DefaultConfigBinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

/**
 * Config Binding {@link BeanPostProcessor}
 *
 * @see EnableConfigBinding
 * @see ConfigBindingRegistrar
 * @since 1.0.0
 */

@Slf4j
public class ConfigBindingBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware, InitializingBean {

    /**
     * The prefix of Configuration Properties
     */
    private final String prefix;

    /**
     * Binding Bean Name
     */
    private final String beanName;

    private ConfigBinder configBinder;

    private ApplicationContext applicationContext;

    private boolean ignoreUnknownFields = true;

    private boolean ignoreInvalidFields = true;

    /**
     * @param prefix   the prefix of Configuration Properties
     * @param beanName the binding Bean Name
     */
    public ConfigBindingBeanPostProcessor(String prefix, String beanName) {
        Assert.notNull(prefix, "The prefix of Configuration Properties must not be null");
        Assert.notNull(beanName, "The name of bean must not be null");
        this.prefix = prefix;
        this.beanName = beanName;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (beanName != null && beanName.equals(this.beanName)) {

            configBinder.bind(prefix, bean);

            if (log.isInfoEnabled()) {
                log.info("The properties of bean [name : " + beanName + "] have been binding by prefix of " +
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

    public ConfigBinder getConfigBinder() {
        return configBinder;
    }

    public void setConfigBinder(ConfigBinder configBinder) {
        this.configBinder = configBinder;
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

        if (configBinder == null) {
            try {
                configBinder = applicationContext.getBean(ConfigBinder.class);
            } catch (BeansException ignored) {
                if (log.isDebugEnabled()) {
                    log.debug("ConfigBinder Bean can't be found in ApplicationContext.");
                }
                // Use Default implementation
                configBinder = createConfigBinder(applicationContext.getEnvironment());
            }
        }

        configBinder.setIgnoreUnknownFields(ignoreUnknownFields);
        configBinder.setIgnoreInvalidFields(ignoreInvalidFields);

    }

    /**
     * Create {@link ConfigBinder} instance.
     *
     * @param environment
     * @return {@link DefaultConfigBinder}
     */
    protected ConfigBinder createConfigBinder(Environment environment) {
        DefaultConfigBinder defaultConfigBinder = new DefaultConfigBinder();
        defaultConfigBinder.setEnvironment(environment);
        return defaultConfigBinder;
    }

}
