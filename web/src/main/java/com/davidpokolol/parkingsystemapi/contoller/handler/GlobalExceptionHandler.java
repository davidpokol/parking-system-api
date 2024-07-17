package com.davidpokolol.parkingsystemapi.contoller.handler;

import com.davidpokolol.parkingsystemapi.model.exception.EntityNotFoundException;
import com.davidpokolol.parkingsystemapi.model.response.ErrorResponse;
import com.davidpokolol.parkingsystemapi.service.model.exception.EnumConversionException;
import com.davidpokolol.parkingsystemapi.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
@Slf4j
@Order(3)
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(
            final EntityNotFoundException exception) {

        log.error("Entity is not existing: {}", exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(List.of(exception.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EnumConversionException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            final EnumConversionException exception) {

        log.error("Unable to convert Enum type to String: {}", exception.getMessage());
        String error = String.format("%s - the value must be one of: %s.",
                StringUtil.lowerCaseFirstCharacter(exception.getEnumType().getSimpleName()),
                Arrays.toString(exception.getEnumType().getEnumConstants())
        );

        ErrorResponse errorResponse = new ErrorResponse(List.of(error));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
            final DataIntegrityViolationException exception) {

        log.error("Data integrity violation: {}", exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(List.of(exception.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            final Exception exception) {

        log.error("Server side problem: {}", exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(List.of(exception.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
