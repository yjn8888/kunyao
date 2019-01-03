package com.kunyao.core.spring.boot.autoconfigure;

import com.kunyao.core.spring.boot.RelaxedConfigBinder;
import com.kunyao.core.spring.util.SpringContextProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
@ConditionalOnClass(Binder.class)
public class RelaxedConfigBinderAutoConfigration {

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public RelaxedConfigBinder relaxedConfigBinder() {
        return new RelaxedConfigBinder();
    }

    @Bean
    public SpringContextProvider springContextUtil(){
        return new SpringContextProvider();
    }
}
