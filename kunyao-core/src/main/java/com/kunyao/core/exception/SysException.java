package com.kunyao.core.exception;

public class SysException extends BaseException{

    public static final String DEFAULT_SYS_ERROR_MESSAGE = "System internal Error! Please try again later OR Contact your administrator to solve .";

    public static final String DEFAULT_SYS_ERROR_CODE = "SYS-ERROR-00001";

    public SysException(){
        this.setErrorCode(DEFAULT_SYS_ERROR_CODE);
        this.setErrorMessage(DEFAULT_SYS_ERROR_MESSAGE);
    }

    public SysException(String code,String message, Throwable cause){
        super(code,message,cause);
    }

    public SysException(Throwable cause) {
        super(cause);
        this.setErrorCode(DEFAULT_SYS_ERROR_CODE);
        this.setErrorMessage(DEFAULT_SYS_ERROR_MESSAGE);
    }

    public static SysException getSysException(String code,String message, Throwable cause){
        return new SysException(code,message,cause);
    }

    public static SysException getSysException(Throwable cause){
        return new SysException(cause);
    }
}
