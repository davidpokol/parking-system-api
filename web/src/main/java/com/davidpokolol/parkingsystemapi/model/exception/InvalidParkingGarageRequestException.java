package com.davidpokolol.parkingsystemapi.model.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidParkingGarageRequestException extends RuntimeException {

    private final List<String> errors;

    public InvalidParkingGarageRequestException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
}
