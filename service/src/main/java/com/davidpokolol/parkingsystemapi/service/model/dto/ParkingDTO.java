package com.davidpokolol.parkingsystemapi.service.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.VEHICLE_LICENSE_PLATE_PATTERN;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.VEHICLE_PATTERN_DOES_NOT_MATCH;


public record ParkingDTO(
        Long id,
        @Pattern(regexp = VEHICLE_LICENSE_PLATE_PATTERN,
                message = VEHICLE_PATTERN_DOES_NOT_MATCH,
                flags = Pattern.Flag.CASE_INSENSITIVE)
        @NotNull
        String vehicleLicensePlate,
        @NotNull
        Long parkingGarageId,
        @Past
        @NotNull
        LocalDateTime startTime,
        @Past
        @NotNull
        LocalDateTime endTime
) {

}
