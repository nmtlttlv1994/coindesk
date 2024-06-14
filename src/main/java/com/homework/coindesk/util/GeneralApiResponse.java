package com.homework.coindesk.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralApiResponse<T>{
    private long ts;
    private int status;
    private boolean success;
    private T data;

    private String message;

    public GeneralApiResponse(HttpStatus status) {
        this.status = status.value();
        this.success = status.is2xxSuccessful();
        this.ts = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public GeneralApiResponse(HttpStatus status, T data) {
        this(status);
        this.data = data;
    }

    public GeneralApiResponse(HttpStatus status, T data, String errorCode) {
        this(status, data);
        this.message = errorCode;
    }

    public GeneralApiResponse(HttpStatus status, String errorCode) {
        this(status);
        this.message = errorCode;
    }

    public static <T> GeneralApiResponse<T> ok() {
        return new GeneralApiResponse<>(HttpStatus.OK);
    }

    public static <T> GeneralApiResponse<T> ok(T data) {
        return new GeneralApiResponse<>(HttpStatus.OK, data);
    }

    public static <T> GeneralApiResponse<T> error(String errorCode) {
        return new GeneralApiResponse<>(HttpStatus.SERVICE_UNAVAILABLE, errorCode);
    }
}
