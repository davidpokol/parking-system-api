package com.davidpokolol.parkingsystemapi.model.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ErrorResponse(@NotEmpty List<String> errors) {
    public void addError(final @NotBlank String error) {
        errors.add(error);
    }
}
