package com.davidpokolol.parkingsystemapi.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(name = "Error Response")
public record ErrorResponse(@NotEmpty List<String> errors) {
    public void addError(final @NotBlank String error) {
        errors.add(error);
    }
}
