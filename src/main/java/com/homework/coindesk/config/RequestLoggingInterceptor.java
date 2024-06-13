package com.homework.coindesk.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
@Log4j2
public class RequestLoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Request URL: {}", request.getRequestURI());
        log.info("Method: {}", request.getMethod());
        log.info("Headers: {}", request.getHeaderNames());

        // Log request body
        logRequestBody(request);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (response instanceof ContentCachingResponseWrapper) {
            ContentCachingResponseWrapper responseWrapper = (ContentCachingResponseWrapper) response;

            // Log response body
            logResponseBody(responseWrapper);
        }
    }

    private void logRequestBody(HttpServletRequest request) {
        try {
            String requestBody = new BufferedReader(new InputStreamReader(request.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));
            log.info("Request Body: {}", requestBody);
        } catch (IOException e) {
            log.error("Error reading request body: {}", e.getMessage());
        }
    }

    private void logResponseBody(ContentCachingResponseWrapper responseWrapper) {
        try {
            byte[] buf = responseWrapper.getContentAsByteArray();
            if (buf.length > 0) {
                String responseBody = new String(buf, 0, buf.length, responseWrapper.getCharacterEncoding());
                log.info("Response Body: {}", responseBody);
            }
        } catch (IOException e) {
            log.error("Error reading response body: {}", e.getMessage());
        }
    }
}