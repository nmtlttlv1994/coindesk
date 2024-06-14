package com.homework.coindesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author DungNT315@fpt.com
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Server encountered an unexpected condition")
public class InternalServerErrorException extends RuntimeException {

    private final String message;

    public InternalServerErrorException(String errorCode) {
        super(errorCode);
        this.message = errorCode;
    }

    public InternalServerErrorException(Throwable throwable) {
        super(throwable);
        this.message = throwable.getMessage();
    }

    public InternalServerErrorException(String errorCode, Throwable throwable) {
        super(errorCode, throwable);
        this.message = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
