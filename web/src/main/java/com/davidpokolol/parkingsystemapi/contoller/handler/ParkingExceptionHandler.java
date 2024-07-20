package com.davidpokolol.parkingsystemapi.contoller.handler;

import com.davidpokolol.parkingsystemapi.contoller.ParkingController;
import com.davidpokolol.parkingsystemapi.model.exception.InvalidParkingRequestException;
import com.davidpokolol.parkingsystemapi.model.response.ErrorResponse;
import com.davidpokolol.parkingsystemapi.model.exception.InvalidParkingDtoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@Order(1)
@RestControllerAdvice(assignableTypes = ParkingController.class)
public class ParkingExceptionHandler {

    @ExceptionHandler(value = InvalidParkingRequestException.class)
    public ResponseEntity<ErrorResponse> invalidParkingRequestHandler(
            final InvalidParkingRequestException exception) {

        log.error("Invalid parking request: {}", exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrors());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidParkingDtoException.class)
    public ResponseEntity<ErrorResponse> invalidParkingDtoHandler(
            final InvalidParkingDtoException exception) {

        log.error(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(List.of(exception.getMessage()));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
