package com.davidpokolol.parkingsystemapi.model.exception;

import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class InvalidVehicleRequestException extends RuntimeException {

    private final List<String> errors;

    public InvalidVehicleRequestException(
            @NonNull final String message,
            @NonNull final List<String> errors) {
        super(message);
        this.errors = errors;
    }
}
