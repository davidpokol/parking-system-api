package com.davidpokolol.parkingsystemapi.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "Parking Garage")
public record ParkingGarageDTO(
        Long id,
        @NotNull
        @Size(min = 10, max = 60)
        String address,
        @NotNull
        @Min(value = 10)
        @Max(value = 1_000)
        Integer parkingSpaces
) {
}
