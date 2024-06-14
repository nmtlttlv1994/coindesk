package com.homework.coindesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author TuanPA44@fpt.com
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The server didn’t understand the request")
public class BadRequestException extends RuntimeException {

    private final String message;

    public BadRequestException(String errorCode) {
        super(errorCode);
        this.message = errorCode;
    }

    public BadRequestException(Throwable throwable) {
        super(throwable);
        this.message = throwable.getMessage();
    }

    public BadRequestException(String errorCode, Throwable throwable) {
        super(errorCode, throwable);
        this.message = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
