package com.homework.coindesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author TuanPA44@fpt.com
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Required authentication was not provided")
public class AuthenticationException extends RuntimeException {

    private final String message;

    public AuthenticationException(String errorCode) {
        super(errorCode);
        this.message = errorCode;
    }

    public AuthenticationException(Throwable throwable) {
        super(throwable);
        this.message = throwable.getMessage();
    }

    public AuthenticationException(String errorCode, Throwable throwable) {
        super(errorCode, throwable);
        this.message = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
