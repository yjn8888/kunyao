package com.kunyao.core.exception;

public class SysException extends BaseException{

    public SysException(String message) {
        super(message);
    }

    public SysException(Throwable cause) {
        super(cause);
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }
}
