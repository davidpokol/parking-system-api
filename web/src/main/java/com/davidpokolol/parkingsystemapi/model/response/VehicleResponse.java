package com.davidpokolol.parkingsystemapi.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.davidpokolol.parkingsystemapi.constant.VehicleConstants.VEHICLE_LICENSE_PLATE_PATTERN;
import static com.davidpokolol.parkingsystemapi.constant.VehicleConstants.VEHICLE_PATTERN_DOES_NOT_MATCH;

@Schema(name = "Vehicle Response")
public record VehicleResponse(
        @NotNull
        Long id,
        @Pattern(regexp = VEHICLE_LICENSE_PLATE_PATTERN,
                message = VEHICLE_PATTERN_DOES_NOT_MATCH,
                flags = Pattern.Flag.CASE_INSENSITIVE)
        @NotNull
        String licensePlate,
        @Schema(allowableValues = {"M1", "N1", "N2", "N3", "L", "O", "T", "C",})
        @NotNull
        String vehicleCategory
) {
}
