package com.kunyao.log.trace.intercepter;

import com.kunyao.log.trace.LogConstant;
import com.kunyao.log.trace.LogTraceSerialContext;
import com.kunyao.log.trace.annotation.TraceIgnore;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class SpringMVCLogTraceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        try{
            if(o instanceof HandlerMethod){
                HandlerMethod handlerMethod = (HandlerMethod)o;
                if(isStartTrace(handlerMethod)){
                    String invokeId = getInvokeId(httpServletRequest);
                    LogTraceSerialContext.handleInvokeId(invokeId);
                }
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        LogTraceSerialContext.removeLogTraceSerialContext();
    }

    /**
     *  Get trace id
     * @param httpServletRequest
     * @return
     */
    private String getInvokeId(HttpServletRequest httpServletRequest){
        String invokeId = httpServletRequest.getHeader(LogConstant.TRACE_ID);
        if(StringUtils.isBlank(invokeId)){
            invokeId = httpServletRequest.getParameter(LogConstant.TRACE_ID);
        }
        if(StringUtils.isBlank(invokeId)){
            invokeId = UUID.randomUUID().toString();
        }
        return invokeId;
    }

    /**
     * Determine whether tracking
     * @param handlerMethod
     * @return
     */
    private boolean isStartTrace(HandlerMethod handlerMethod){
        try{
            TraceIgnore traceIgnore = handlerMethod.getBeanType().getAnnotation(TraceIgnore.class);
            if(traceIgnore==null){
                return !handlerMethod.hasMethodAnnotation(TraceIgnore.class);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return false;
    }
}
