package com.homework.coindesk.exception;

import com.homework.coindesk.util.GeneralApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    /* 400 */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GeneralApiResponse<Object> handleBusinessException(BusinessException ex) {
        log.error(ex.getMessage(), ex);
        return GeneralApiResponse.error(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    /* 400 */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GeneralApiResponse<Object> handleBadRequestException(BadRequestException ex) {
        log.error(ex.getMessage(), ex);
        return GeneralApiResponse.error(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    /* 500 */
    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public GeneralApiResponse<Object> handleInternalServerErrorException(InternalServerErrorException ex) {
        log.error(ex.getMessage(), ex);
        return GeneralApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    /* 401 */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public GeneralApiResponse<Object> handleAuthenticationException(AuthenticationException ex) {
        return GeneralApiResponse.error(HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

    /* 403 */
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public GeneralApiResponse<Object> handleAuthorizationException(AuthorizationException ex) {
        log.error(ex.getMessage(), ex);
        return GeneralApiResponse.error(HttpStatus.FORBIDDEN.getReasonPhrase());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GeneralApiResponse<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        return GeneralApiResponse.error(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}