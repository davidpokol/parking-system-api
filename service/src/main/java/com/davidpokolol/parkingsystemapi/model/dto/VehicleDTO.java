package com.davidpokolol.parkingsystemapi.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.davidpokolol.parkingsystemapi.constant.VehicleConstants.VEHICLE_LICENSE_PLATE_PATTERN;
import static com.davidpokolol.parkingsystemapi.constant.VehicleConstants.VEHICLE_PATTERN_DOES_NOT_MATCH;

@Schema(name = "Vehicle")
public record VehicleDTO(
        Long id,
        @NotNull
        @Pattern(regexp = VEHICLE_LICENSE_PLATE_PATTERN,
                message = VEHICLE_PATTERN_DOES_NOT_MATCH,
                flags = Pattern.Flag.CASE_INSENSITIVE)
        String licensePlate,

        @Enumerated(EnumType.STRING)
        @Schema(allowableValues = {"M1", "N1", "N2", "N3", "L", "O", "T", "C",})
        @NotNull
        String vehicleCategory
) {
}