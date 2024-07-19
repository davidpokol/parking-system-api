package com.davidpokolol.parkingsystemapi.model.exception;

import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class InvalidParkingRequestException extends RuntimeException {

    private final List<String> errors;

    public InvalidParkingRequestException(
            @NonNull final String message,
            @NonNull final List<String> errors) {
        super(message);
        this.errors = errors;
    }
}
