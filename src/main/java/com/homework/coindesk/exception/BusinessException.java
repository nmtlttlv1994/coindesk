package com.homework.coindesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author TuanPA44@fpt.com
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "")
public class BusinessException extends RuntimeException {

    private final String message;

    public BusinessException(Throwable throwable) {
        super(throwable);
        this.message = throwable.getMessage();
    }

    public BusinessException(String errorCode, Throwable throwable) {
        super(errorCode, throwable);
        this.message = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
