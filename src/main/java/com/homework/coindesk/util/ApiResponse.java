package com.homework.coindesk.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse <T>{
    private long ts;
    private int status;
    private boolean success;
    private T data;

    private String message;

    public ApiResponse(HttpStatus status) {
        this.status = status.value();
        this.success = status.is2xxSuccessful();
        this.ts = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public ApiResponse(HttpStatus status, T data) {
        this(status);
        this.data = data;
    }

    public ApiResponse(HttpStatus status, T data, String errorCode) {
        this(status, data);
        this.message = errorCode;
    }

    public ApiResponse(HttpStatus status, String errorCode) {
        this(status);
        this.message = errorCode;
    }

    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<>(HttpStatus.OK);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(HttpStatus.OK, data);
    }

    public static <T> ApiResponse<T> error(String errorCode) {
        return new ApiResponse<>(HttpStatus.SERVICE_UNAVAILABLE, errorCode);
    }
}
