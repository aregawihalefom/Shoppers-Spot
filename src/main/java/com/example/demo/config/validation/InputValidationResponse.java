package com.example.demo.config.validation;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class InputValidationResponse {

    private Set<CustomFieldError> errors = new HashSet<>();

}
