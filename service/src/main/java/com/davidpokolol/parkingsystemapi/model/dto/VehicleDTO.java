package com.davidpokolol.parkingsystemapi.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.davidpokolol.parkingsystemapi.constant.VehicleConstants.VEHICLE_LICENSE_PLATE_PATTERN;
import static com.davidpokolol.parkingsystemapi.constant.VehicleConstants.VEHICLE_PATTERN_DOES_NOT_MATCH;

public record VehicleDTO(
        Long id,
        @Pattern(regexp = VEHICLE_LICENSE_PLATE_PATTERN,
                message = VEHICLE_PATTERN_DOES_NOT_MATCH,
                flags = Pattern.Flag.CASE_INSENSITIVE)
        @NotNull
        String licensePlate,

        @NotNull
        String vehicleCategory
) {
}
