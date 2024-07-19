package com.davidpokolol.parkingsystemapi.service.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public record ParkingGarageDTO(
        Long id,
        @NotBlank
        @Size(min = 10, max = 60)
        String address,
        @NotNull
        @Range(min = 10, max = 1_000)
        Integer parkingSpaces
) {
}
