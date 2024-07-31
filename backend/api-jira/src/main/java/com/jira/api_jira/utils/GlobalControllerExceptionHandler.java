package com.jira.api_jira.utils;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorApi constraintViolationException(ConstraintViolationException ex) {
        return new ErrorApi(false, 500, ex.getMessage());
    }

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorApi handleNoHandlerFoundException(Exception ex) {
        return new ErrorApi(false, 404, ex.getMessage());
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorApi unknownException(Exception ex) {
        return new ErrorApi(false, 500, ex.getMessage());
    }
}
