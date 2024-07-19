package com.davidpokolol.parkingsystemapi.model.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ParkingGarageResponse(
        @NotNull
        Long id,
        @NotBlank
        String address,
        @NotNull
        Integer parkingSpaces
) {
}
