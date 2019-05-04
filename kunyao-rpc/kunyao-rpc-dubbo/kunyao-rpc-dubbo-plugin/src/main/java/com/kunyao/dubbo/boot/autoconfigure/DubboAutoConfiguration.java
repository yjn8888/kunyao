package com.kunyao.dubbo.boot.autoconfigure;

import com.kunyao.dubbo.boot.contant.DubboContant;
import com.kunyao.logging.trace.LogTraceSerialContext;
import org.apache.dubbo.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.kunyao.util.SystemUtils.getIP;
import static com.kunyao.util.SystemUtils.getJvmName;

@Configuration
@EnableConfigurationProperties({DubboProperties.class})
public class DubboAutoConfiguration {

    @Autowired
    private DubboProperties dubboProperties;
    
    @Bean
    @ConditionalOnMissingBean
    public ApplicationConfig application() {
        return new ApplicationConfig(DubboContant.DEFAULT_APPLICATION_PREFIX + getIP() + "-" + getJvmName());
    }

    @Bean
    @ConditionalOnMissingBean
    public RegistryConfig registry() {
        return dubboProperties.getRegistry();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(LogTraceSerialContext.class)
    public ProviderConfig provider() {
        ProviderConfig provider = new ProviderConfig();
        provider.setFilter("com.kunyao.dubbo.filter.trace.ProviderRpcTraceFilter");
        provider.setTimeout(dubboProperties.getGlobalTimeout());
        provider.setRetries(0);
        return provider;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(LogTraceSerialContext.class)
    public ConsumerConfig consumer() {
        ConsumerConfig consumer = new ConsumerConfig();
        consumer.setFilter("com.kunyao.dubo.filter.trace.ConsumerRpcTraceFilter");
        consumer.setCheck(false);
        return consumer;
    }

    @Bean
    @ConditionalOnMissingBean
    public ProtocolConfig dubbo() {
        ProtocolConfig dubbo = new ProtocolConfig(DubboContant.DEFAULT_PROTOCOL,DubboContant.DEFAULT_REMOTING_PORT);
        dubbo.setId(DubboContant.DEFAULT_PROTOCOL);
        dubbo.setSerialization(DubboContant.KRYO_REMOTING_SERIALIZATION);
        dubbo.setServer(DubboContant.DEFAULT_REMOTING_SERVER);
        dubbo.setClient(DubboContant.DEFAULT_REMOTING_CLIENT);
        dubbo.setCharset(DubboContant.DEFAULT_CHARSET);
        dubbo.setThreadpool(DubboContant.DEFAULT_REMOTING_THREADPOOL);
        dubbo.setThreads(DubboContant.DEFAULT_REMOTING_THREADS);
        dubbo.setIothreads(DubboContant.DEFAULT_REMOTING_IOTHREADS);
        dubbo.setQueues(DubboContant.DEFAULT_QUEUES);
        dubbo.setAccepts(DubboContant.DEFAULT_REMOTING_ACCEPTS);
        dubbo.setBuffer(DubboContant.DEFAULT_BUFFER_SIZE);
        dubbo.setPayload(DubboContant.DEFAULT_PAYLOAD);
        dubbo.setDefault(true);
        return dubbo;
    }

}
