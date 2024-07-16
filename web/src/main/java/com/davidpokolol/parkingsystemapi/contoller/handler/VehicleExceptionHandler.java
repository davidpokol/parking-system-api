package com.davidpokolol.parkingsystemapi.contoller.handler;

import com.davidpokolol.parkingsystemapi.contoller.VehicleController;
import com.davidpokolol.parkingsystemapi.model.exception.InvalidVehicleRequestException;
import com.davidpokolol.parkingsystemapi.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = VehicleController.class)
@Slf4j
@Order(1)
public class VehicleExceptionHandler {

    @ExceptionHandler(value = InvalidVehicleRequestException.class)
    public ResponseEntity<ErrorResponse> invalidVehicleHandler(InvalidVehicleRequestException exception) {

        log.error("Invalid vehicle request: {}", exception.getMessage());
        ErrorResponse badRequestError = new ErrorResponse(exception.getErrors());
        return ResponseEntity.badRequest()
                .body(badRequestError);
    }
}
