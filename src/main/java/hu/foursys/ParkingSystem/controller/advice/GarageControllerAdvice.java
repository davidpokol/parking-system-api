package hu.foursys.ParkingSystem.controller.advice;

import hu.foursys.ParkingSystem.exception.DatabaseConstraintViolationException;
import hu.foursys.ParkingSystem.response.BadRequestError;
import hu.foursys.ParkingSystem.exception.InvalidRequestException;
import hu.foursys.ParkingSystem.exception.ParkingGarageNotFoundException;
import hu.foursys.ParkingSystem.response.DatabaseError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GarageControllerAdvice {
    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<BadRequestError> invalidGarageHandler(InvalidRequestException invalidCarRequestException) {
        BadRequestError badRequestError = new BadRequestError(invalidCarRequestException.getErrors());

        return ResponseEntity.badRequest()
                .body(badRequestError);
    }

    @ExceptionHandler(value = DatabaseConstraintViolationException.class)
    public ResponseEntity<DatabaseError> constraintViolationHandler(
            DatabaseConstraintViolationException databaseConstraintViolationException) {
        DatabaseError databaseError = new DatabaseError(databaseConstraintViolationException.getMessage());

        return ResponseEntity.status(409)
                .body(databaseError);
    }

    @ExceptionHandler(value = ParkingGarageNotFoundException.class)
    public ResponseEntity<DatabaseError> parkingGarageNotFoundHandler
            (ParkingGarageNotFoundException parkingGarageNotFoundException) {
        DatabaseError databaseError = new DatabaseError(parkingGarageNotFoundException.getMessage());

        return ResponseEntity.status(404).
                body(databaseError);
    }
}
