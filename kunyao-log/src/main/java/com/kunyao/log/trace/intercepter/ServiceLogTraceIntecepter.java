package com.kunyao.log.trace.intercepter;

import com.kunyao.log.trace.LogTraceSerialContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ServiceLogTraceIntecepter {

    @Pointcut("@annotation(com.kunyao.log.trace.annotation.LogTrace)")
    private void excuteService(){}

    @Before("excuteService()")
    private void before(JoinPoint joinPoint){
        LogTraceSerialContext.handleInvokeId(null);
    }

    @AfterReturning("excuteService()")
    private void after(JoinPoint joinPoint){

    }
}
