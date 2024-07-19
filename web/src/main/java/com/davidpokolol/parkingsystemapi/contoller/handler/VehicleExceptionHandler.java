package com.davidpokolol.parkingsystemapi.contoller.handler;

import com.davidpokolol.parkingsystemapi.contoller.VehicleController;
import com.davidpokolol.parkingsystemapi.model.exception.InvalidVehicleRequestException;
import com.davidpokolol.parkingsystemapi.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(1)
@RestControllerAdvice(assignableTypes = VehicleController.class)
public class VehicleExceptionHandler {

    @ExceptionHandler(value = InvalidVehicleRequestException.class)
    public ResponseEntity<ErrorResponse> invalidVehicleHandler(
            final InvalidVehicleRequestException exception) {

        log.error("Invalid vehicle request: {}", exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrors());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
