package com.example.demo.dto;

public class CustomExceptionDto extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CustomExceptionDto(String msg){
        super(msg);
    }

}
