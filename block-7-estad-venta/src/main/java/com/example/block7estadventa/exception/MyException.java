package com.example.block7estadventa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class MyException extends RuntimeException {
    public MyException(String message) {
        super(message);
    }
}
