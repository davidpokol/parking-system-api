package com.davidpokolol.parkingsystemapi.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class RequestValidationHandlerUtil {

    public static List<String> getRequestErrors(BindingResult bindingResult) {
        return bindingResult
                .getFieldErrors()
                .stream()
                .map(RequestValidationHandlerUtil::fieldErrorToMessage)
                .toList();
    }

    private static String fieldErrorToMessage(FieldError fieldError) {
        return fieldError.getField() + " - " + fieldError.getDefaultMessage();
    }
}