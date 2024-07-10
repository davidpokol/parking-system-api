package com.davidpokolol.parkingsystemapi.service.model.exception;

public class NotEnoughParkingSpaceException extends RuntimeException {

    public NotEnoughParkingSpaceException(String message) {
        super(message);
    }

    public NotEnoughParkingSpaceException(String message, Throwable cause) {
        super(message, cause);
    }
}
