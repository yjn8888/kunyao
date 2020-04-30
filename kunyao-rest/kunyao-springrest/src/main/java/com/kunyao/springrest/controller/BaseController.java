package com.kunyao.springrest.controller;

import com.kunyao.core.entity.invoke.InvokeResult;
import com.kunyao.core.exception.BaseException;
import com.kunyao.core.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.kunyao.core.entity.invoke.InvokeResult.buildInvokeResult;

/**
 * @Author The little blacksmith
 * @Description controller
 * @Date 2020/3/31 15:38
 * @Version 1.0
 */
@Slf4j
public abstract class BaseController {

    /**
     * @Author The little blacksmith
     * @Description 异常统一处理
     * @Date 2020/4/17
     * @Param [request, response, e]
     * @return com.kunyao.core.entity.invoke.InvokeResult<?>
     */
    @ExceptionHandler
    @ResponseBody
    public InvokeResult<?> handleException(HttpServletRequest request, HttpServletResponse response, Exception e){
        log.error(e.getMessage(),e);
        log.error("uri={},errorMessage={}",request.getRequestURI(),e.getMessage());
        if(e instanceof BaseException){
            return buildInvokeResult((BaseException)e);
        }
        return buildInvokeResult(new SysException(e));
    }

}
