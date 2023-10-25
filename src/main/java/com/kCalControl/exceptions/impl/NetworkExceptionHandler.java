package com.kCalControl.exceptions.impl;

import com.kCalControl.exceptions.NetworkException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class NetworkExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NetworkException.class)
    protected ResponseEntity<Object> handleConflict(NetworkException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", ex.getHttpStatus());
        body.put("code", ex.getHttpStatus().value());
        body.put("timestamp", new Date());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, ex.getHttpStatus());
    }
}
