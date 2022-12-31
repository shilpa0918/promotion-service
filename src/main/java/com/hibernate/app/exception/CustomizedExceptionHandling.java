package com.hibernate.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomerDoesNotExistException.class)
    public ResponseEntity<Object> handleException(CustomerDoesNotExistException exception, WebRequest request) {
        CustomerExceptionResponse customerExceptionResponse = new CustomerExceptionResponse();
        customerExceptionResponse.setMessage("Not Found");
        customerExceptionResponse.setDateTime(LocalDateTime.now());
        ResponseEntity<Object> entity = new ResponseEntity<>(customerExceptionResponse, HttpStatus.NOT_FOUND);
        return entity;
    }
}

