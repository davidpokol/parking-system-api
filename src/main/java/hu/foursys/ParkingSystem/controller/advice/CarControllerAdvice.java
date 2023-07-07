package hu.foursys.ParkingSystem.controller.advice;

import hu.foursys.ParkingSystem.exception.DatabaseConstraintViolationException;
import hu.foursys.ParkingSystem.response.BadRequestError;
import hu.foursys.ParkingSystem.exception.CarNotFoundException;
import hu.foursys.ParkingSystem.exception.InvalidRequestException;
import hu.foursys.ParkingSystem.response.DatabaseError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CarControllerAdvice {

    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<BadRequestError> invalidCarHandler(InvalidRequestException invalidCarRequestException) {
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

    @ExceptionHandler(value = CarNotFoundException.class)
    public ResponseEntity<DatabaseError> carNotFoundHandler(CarNotFoundException carNotFoundException) {
        DatabaseError databaseError = new DatabaseError(carNotFoundException.getMessage());

        return ResponseEntity.status(404).
                body(databaseError);
    }

}
