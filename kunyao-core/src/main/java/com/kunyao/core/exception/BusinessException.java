package com.kunyao.core.exception;

/**
 * 此类描述的是：业务异常类
 * @author: 
 * @since : 2015年7月21日
 */
public class BusinessException extends BaseException {
    private static final long serialVersionUID = 2416035725705871216L;
    
    /**
     * @author: 
     * @category moduleNameChs 中文模块名
     */
    private String moduleNameChs;
    
    
    /**
     * @author: 
     * @category moduleNameEng 英文模块名
     */
    private String moduleNameEng;
    
    
    /**
     * @author: 
     * @category methodNameChs 中文方法名
     */
    private String methodNameChs;
    
    /**
     * @author: 
     * @category methodNameEng 英文方法名
     */
    private String methodNameEng;
    
    /**
     * @author: 
     * @category businessErrorCode 业务错误代码
     */
    private String businessErrorCode;
    
    /**
     * @author: 
     * @category businessErrorMessage 业务错误信息
     */
    private String businessErrorMessage;

    
    /**
     * @author:
     * @since: 2015年7月21日
     * 
     * @category:
     * @param moduleNameChs
     * @param moduleNameEng
     * @param methodNameChs
     * @param methodNameEng
     * @param businessErrorCode
     * @param businessErrorMessage
     */
    public BusinessException(String moduleNameChs, String moduleNameEng, String methodNameChs, String methodNameEng,
            String businessErrorCode, String businessErrorMessage) {
        super(businessErrorCode + " : " + businessErrorMessage);
        this.moduleNameChs = moduleNameChs;
        this.moduleNameEng = moduleNameEng;
        this.methodNameChs = methodNameChs;
        this.methodNameEng = methodNameEng;
        this.businessErrorCode = businessErrorCode;
        this.businessErrorMessage = businessErrorMessage;
    }

    
    /**
     * @author:
     * @category:
     * @param moduleNameChs
     * @param moduleNameEng
     * @param methodNameChs
     * @param methodNameEng
     * @param businessErrorCode
     * @param businessErrorMessage
     * @param cause
     */
    public BusinessException(String moduleNameChs, String moduleNameEng, String methodNameChs, String methodNameEng,
            String businessErrorCode, String businessErrorMessage, Throwable cause) {
        super(businessErrorMessage, cause);
        this.moduleNameChs = moduleNameChs;
        this.moduleNameEng = moduleNameEng;
        this.methodNameChs = methodNameChs;
        this.methodNameEng = methodNameEng;
        this.businessErrorCode = businessErrorCode;
        this.businessErrorMessage = businessErrorMessage;
    }
    

    
    /**
     * @author:
     * @category:
     * @param businessErrorCode
     * @param businessErrorMessage
     */
    public BusinessException(String businessErrorCode, String businessErrorMessage) {
        super(businessErrorCode + " : " + businessErrorMessage);
        this.businessErrorCode = businessErrorCode;
        this.businessErrorMessage = businessErrorMessage;
    }
    
    public String getModuleNameChs() {
        return moduleNameChs;
    }


    public void setModuleNameChs(String moduleNameChs) {
        this.moduleNameChs = moduleNameChs;
    }


    public String getModuleNameEng() {
        return moduleNameEng;
    }


    public void setModuleNameEng(String moduleNameEng) {
        this.moduleNameEng = moduleNameEng;
    }


    public String getMethodNameChs() {
        return methodNameChs;
    }


    public void setMethodNameChs(String methodNameChs) {
        this.methodNameChs = methodNameChs;
    }


    public String getMethodNameEng() {
        return methodNameEng;
    }


    public void setMethodNameEng(String methodNameEng) {
        this.methodNameEng = methodNameEng;
    }


    
    public String getBusinessErrorCode() {
        return businessErrorCode;
    }


    public String getBusinessErrorMessage() {
        return businessErrorMessage;
    }


    @Override
    public String toString() {
        return "BusinessException [moduleNameChs=" + moduleNameChs + ", moduleNameEng=" + moduleNameEng
                + ", methodNameChs=" + methodNameChs + ", methodNameEng=" + methodNameEng + ", businessErrorCode="
                + businessErrorCode + ", businessErrorMessage=" + businessErrorMessage + "]";
    }
}
