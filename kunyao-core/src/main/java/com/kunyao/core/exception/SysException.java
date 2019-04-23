package com.kunyao.core.exception;

public class SysException extends BaseException{
    public SysException(){}

    public SysException(String message) {
        super(message);
    }

    public SysException(Throwable cause) {
        super(cause);
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }

    public static SysException getSysException(String message){
        return new SysException(message);
    }
}
