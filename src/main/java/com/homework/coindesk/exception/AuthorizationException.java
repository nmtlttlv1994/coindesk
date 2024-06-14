package com.homework.coindesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author TuanPA44@fpt.com
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "You are forbidden to perform this operation")
public class AuthorizationException extends RuntimeException {

    private final String message;

    public AuthorizationException(String errorCode) {
        super(errorCode);
        this.message = errorCode;
    }

    public AuthorizationException(Throwable throwable) {
        super(throwable);
        this.message = throwable.getMessage();
    }


    public AuthorizationException(String errorCode, Throwable throwable) {
        super(errorCode, throwable);
        this.message = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
