package com.kCalControl.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

@Getter
public class NetworkException extends RuntimeException{

    private final HttpStatus httpStatus;

    public NetworkException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
