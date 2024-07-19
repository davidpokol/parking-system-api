package com.davidpokolol.parkingsystemapi.model.exception;

import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class InvalidParkingGarageRequestException extends RuntimeException {

    private final List<String> errors;

    public InvalidParkingGarageRequestException(
            @NonNull final String message,
            @NonNull final List<String> errors) {
        super(message);
        this.errors = errors;
    }
}
