package com.kunyao.core.exception;
import org.apache.commons.lang3.StringUtils;

import com.kunyao.core.constant.InvokeRelatedConstant;
import com.kunyao.core.entity.factory.InvokeResultFactory;
import com.kunyao.core.entity.invoke.InvokeResult;
import com.kunyao.core.entity.invoke.InvokeResultWithGenericType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用异常处理类
 * @author: 
 * @since : 2015年7月21日
 */
@SuppressWarnings("deprecation")
public class ExceptionHandler {
    
    
    /**
     * @author: 
     * @category logger 日志输出变量
     * @since: 2015年7月21日
     */
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    
    
    /**
     * 异常处理方法
     * @category
     * @author: 
     * @since: 2015年7月21日
     * @param e
     */
    public static void handle(BusinessException e) {
        //logger.error(e.getMessage(), e);
        logger.error(e.getMessage(),e);
    }

    
    /**
     * 异常处理方法
     * @category
     * @author: 
     * @since: 2015年7月21日
     * @param e
     */
    public static void handle(BaseException e) {
        logger.error(e.getMessage(), e);
    }
    
    
    /**
     * 异常处理方法
     * @category
     * @author: 
     * @since: 2015年7月22日
     * @param e
     */
    public static void handle(Exception e) {
        logger.error(e.getMessage(), e);
    }
    
    
    /**
     * TODO:handle(Exception e, Logger logger)
     * @category
     * @author: 
     * @since: 2015年8月6日
     * @param e
     * @param logger
     */
    public static void handle(Exception e, Logger logger) {
        logger.error(e.getMessage(), e);
    }

	public static void handle(Exception e, Logger logger, InvokeResult result) {
        if (result == null) {
            result = InvokeResultFactory.getGeneralFailedInvokeResultWithoutData();
        }
        if (e instanceof BusinessException) {
            BusinessException bie = (BusinessException) e;
            result.setResultCode(bie.getBusinessErrorCode());
            if (StringUtils.isEmpty(bie.getBusinessErrorCode())) {
            	result.setResultCode(InvokeRelatedConstant.GENERAL_BIZ_FAIL_CODE);
            }
            result.setResultMessage(bie.getBusinessErrorMessage());
            result.setBizResultMessage(bie.getBusinessErrorMessage());
            result.setException(bie.toString());
            logger.error(e.getMessage());
        } else if (e instanceof BaseException) {
            BaseException be = (BaseException) e;
            result.setResultCode(be.getErrorCode());
            if (StringUtils.isEmpty(be.getErrorCode())) {
            	result.setResultCode(InvokeRelatedConstant.GENERAL_BIZ_FAIL_CODE);
            }
            result.setResultMessage(be.getErrorMessage());
            result.setBizResultMessage(be.getErrorMessage());
            result.setException(be.toString());
            logger.error(e.getMessage(), e);
        } else {
            result.setResultCode(InvokeRelatedConstant.GENERAL_SYSTEM_FAIL_CODE);
            result.setResultMessage(InvokeRelatedConstant.GENERAL_SYSTEM_FAIL_MESSAGE);
            result.setBizResultMessage(InvokeRelatedConstant.GENERAL_SYSTEM_FAIL_MESSAGE);
            result.setException(e.toString());
            logger.error(e.getMessage(), e);
        }
    }
    
    public static void handle(Exception e, Logger logger, InvokeResultWithGenericType<?> result) {
        if (result == null) {
            result = InvokeResultFactory.getFailResult();
        }
        if (e instanceof BusinessException) {
            BusinessException bie = (BusinessException) e;
            result.setResultCode(bie.getBusinessErrorCode());
            if (StringUtils.isEmpty(bie.getBusinessErrorCode())) {
            	result.setResultCode(InvokeRelatedConstant.GENERAL_BIZ_FAIL_CODE);
            }
            result.setResultMessage(bie.getBusinessErrorMessage());
            result.setBizResultMessage(bie.getBusinessErrorMessage());
            result.setException(bie.toString());
            logger.error(e.getMessage());
        } else if (e instanceof BaseException) {
            BaseException be = (BaseException) e;
            result.setResultCode(be.getErrorCode());
            if (StringUtils.isEmpty(be.getErrorCode())) {
            	result.setResultCode(InvokeRelatedConstant.GENERAL_BIZ_FAIL_CODE);
            }
            result.setResultMessage(be.getErrorMessage());
            result.setBizResultMessage(be.getErrorMessage());
            result.setException(be.toString());
            logger.error(e.getMessage(), e);
        } else {
            result.setResultCode(InvokeRelatedConstant.GENERAL_SYSTEM_FAIL_CODE);
            result.setResultMessage(InvokeRelatedConstant.GENERAL_SYSTEM_FAIL_MESSAGE);
            result.setBizResultMessage(InvokeRelatedConstant.GENERAL_SYSTEM_FAIL_MESSAGE);
            result.setException(e.toString());
            logger.error(e.getMessage(), e);
        }
    }
    public static void handle(BusinessException e, Logger logger) {
        //logger.error(e.getMessage(), e);
        logger.error(e.getMessage(),e);
    }
}
