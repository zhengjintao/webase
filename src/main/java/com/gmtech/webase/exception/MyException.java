package com.gmtech.webase.exception;

import lombok.Getter;

@Getter
public class MyException extends RuntimeException {

    private ErrorDetail errorDetails;

    public MyException(String message, ErrorDetail errorDetails) {
        super(message);
        this.errorDetails = errorDetails;
    }
}