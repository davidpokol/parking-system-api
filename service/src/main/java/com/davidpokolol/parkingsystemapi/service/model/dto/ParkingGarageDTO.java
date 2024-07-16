package com.davidpokolol.parkingsystemapi.service.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ParkingGarageDTO(
        Long id,
        @NotBlank
        String address,
        @Min(value = 1)
        int parkingSpaces
) {
}
