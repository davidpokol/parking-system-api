package com.davidpokolol.parkingsystemapi.contoller.handler;

import com.davidpokolol.parkingsystemapi.model.exception.EntityNotFoundException;
import com.davidpokolol.parkingsystemapi.model.response.ErrorResponse;
import com.davidpokolol.parkingsystemapi.service.model.exception.EnumConversionException;
import com.davidpokolol.parkingsystemapi.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
@Slf4j
@Order(2)
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final EntityNotFoundException exception) {

        log.error("Entity is not existing: {}", exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(List.of(exception.getMessage()));
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(EnumConversionException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(EnumConversionException exception) {

        log.error("Unable to convert Enum type to String: {}", exception.getMessage());
        String error = String.format("%s - the value must be one of: %s.",
                StringUtil.lowerCaseFirstCharacter(exception.getEnumType().getSimpleName()),
                Arrays.toString(exception.getEnumType().getEnumConstants())
        );

        ErrorResponse errorResponse = new ErrorResponse(List.of(error));
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception exception) {

        log.error("Server side problem: {}", exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(List.of(exception.getMessage()));
        return ResponseEntity.status(500).body(errorResponse);
    }
}
