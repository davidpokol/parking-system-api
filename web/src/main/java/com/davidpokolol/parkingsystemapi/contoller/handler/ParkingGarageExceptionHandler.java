package com.davidpokolol.parkingsystemapi.contoller.handler;

import com.davidpokolol.parkingsystemapi.contoller.ParkingGarageController;
import com.davidpokolol.parkingsystemapi.model.exception.InvalidParkingGarageRequestException;
import com.davidpokolol.parkingsystemapi.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ParkingGarageController.class)
@Slf4j
@Order(1)
public class ParkingGarageExceptionHandler {

    @ExceptionHandler(value = InvalidParkingGarageRequestException.class)
    public ResponseEntity<ErrorResponse> invalidVehicleHandler(InvalidParkingGarageRequestException exception) {

        log.error("Invalid parking garage request: {}", exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrors());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
