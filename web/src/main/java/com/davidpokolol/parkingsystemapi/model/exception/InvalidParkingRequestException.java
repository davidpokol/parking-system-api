package com.davidpokolol.parkingsystemapi.model.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidParkingRequestException extends RuntimeException {

    private final List<String> errors;

    public InvalidParkingRequestException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
}
