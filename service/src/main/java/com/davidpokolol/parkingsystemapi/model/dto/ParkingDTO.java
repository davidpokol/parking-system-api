package com.davidpokolol.parkingsystemapi.model.dto;

import com.davidpokolol.parkingsystemapi.model.exception.InvalidParkingDtoException;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

import static com.davidpokolol.parkingsystemapi.constant.VehicleConstants.VEHICLE_LICENSE_PLATE_PATTERN;
import static com.davidpokolol.parkingsystemapi.constant.VehicleConstants.VEHICLE_PATTERN_DOES_NOT_MATCH;

@Schema(name = "Parking")
public record ParkingDTO(

        Long id,
        @NotNull
        @Pattern(regexp = VEHICLE_LICENSE_PLATE_PATTERN,
                message = VEHICLE_PATTERN_DOES_NOT_MATCH,
                flags = Pattern.Flag.CASE_INSENSITIVE)
        String vehicleLicensePlate,
        @NotNull
        Long parkingGarageId,
        @NotNull
        @Past
        LocalDateTime startTime,
        @NotNull
        @PastOrPresent
        LocalDateTime endTime
) {

    public ParkingDTO {

        if (startTime != null && endTime != null && startTime.isEqual(endTime)) {
            throw new InvalidParkingDtoException("The 'startTime' and 'endTime' fields must not be equal.");
        }
    }
}