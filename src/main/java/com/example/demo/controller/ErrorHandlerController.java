package com.example.demo.controller;

import com.example.demo.config.validation.CustomFieldError;
import com.example.demo.config.validation.InputValidationResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashSet;
import java.util.Set;

@ControllerAdvice
public class ErrorHandlerController  extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        InputValidationResponse ValidationResponse = new InputValidationResponse();
        Set<CustomFieldError> fieldErrors = new HashSet<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            CustomFieldError fieldError = new CustomFieldError();
            fieldError.setFieldName(((FieldError) error).getField());
            fieldError.setMessage(error.getDefaultMessage());
            fieldErrors.add(fieldError);
        });
        ValidationResponse.setErrors(fieldErrors);
        return new ResponseEntity<>(ValidationResponse, status);
    }



}
