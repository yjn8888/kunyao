package com.kunyao.core.exception;

/**
 * 此类描述的是：业务异常类
 * @author: 
 * @since : 2019年4月21日
 */
public class BizException extends BaseException {
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
     * @since: 2019年4月21日
     * 
     * @category:
     * @param moduleNameChs
     * @param moduleNameEng
     * @param methodNameChs
     * @param methodNameEng
     * @param errorCode
     * @param errorMessage
     */
    public BizException(String moduleNameChs, String moduleNameEng, String methodNameChs, String methodNameEng,
                        String errorCode, String errorMessage) {
        super(errorCode + " : " + errorMessage);
        this.moduleNameChs = moduleNameChs;
        this.moduleNameEng = moduleNameEng;
        this.methodNameChs = methodNameChs;
        this.methodNameEng = methodNameEng;
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
    }

    
    /**
     * @author:
     * @category:
     * @param moduleNameChs
     * @param moduleNameEng
     * @param methodNameChs
     * @param methodNameEng
     * @param errorCode
     * @param errorMessage
     * @param cause
     */
    public BizException(String moduleNameChs, String moduleNameEng, String methodNameChs, String methodNameEng,
                        String errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.moduleNameChs = moduleNameChs;
        this.moduleNameEng = moduleNameEng;
        this.methodNameChs = methodNameChs;
        this.methodNameEng = methodNameEng;
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
    }
    

    
    /**
     * @author:
     * @category:
     * @param errorCode
     * @param errorMessage
     */
    public BizException(String errorCode, String errorMessage) {
        super(errorCode + " : " + errorMessage);
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
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




    @Override
    public String toString() {
        return "BizException [moduleNameChs=" + moduleNameChs + ", moduleNameEng=" + moduleNameEng
                + ", methodNameChs=" + methodNameChs + ", methodNameEng=" + methodNameEng + ", errorCode="
                + getErrorCode() + ", errorMessage=" + getErrorMessage() + "]";
    }
}
