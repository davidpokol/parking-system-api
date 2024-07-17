package com.davidpokolol.parkingsystemapi.contoller.handler;

import com.davidpokolol.parkingsystemapi.contoller.ParkingController;
import com.davidpokolol.parkingsystemapi.model.exception.InvalidParkingRequestException;
import com.davidpokolol.parkingsystemapi.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ParkingController.class)
@Slf4j
@Order(1)
public class ParkingExceptionHandler {

    @ExceptionHandler(value = InvalidParkingRequestException.class)
    public ResponseEntity<ErrorResponse> invalidVehicleHandler(InvalidParkingRequestException exception) {

        log.error("Invalid parking request: {}", exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrors());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
