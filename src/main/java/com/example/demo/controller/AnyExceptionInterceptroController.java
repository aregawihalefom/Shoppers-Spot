package com.example.demo.controller;

import com.example.demo.dto.CustomExceptionDto;
import com.example.demo.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class AnyExceptionInterceptroController {

    @ExceptionHandler(value = {CustomExceptionDto.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageDto handleExceiptions(CustomExceptionDto exceptionDto , WebRequest webRequest){

        ErrorMessageDto message = new ErrorMessageDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                exceptionDto.getMessage(),
                webRequest.getDescription(false));

        return message;
    }

}
