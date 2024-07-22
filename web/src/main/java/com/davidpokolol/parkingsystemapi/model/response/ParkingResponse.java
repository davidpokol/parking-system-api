package com.davidpokolol.parkingsystemapi.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

@Schema(name = "Parking Response")
public record ParkingResponse(
        @NotNull
        Long id,
        @NotNull
        VehicleResponse vehicle,
        @NotNull
        ParkingGarageResponse parkingGarage,
        @NotNull
        @Past
        LocalDateTime startTime,
        @NotNull
        @PastOrPresent
        LocalDateTime endTime
) {
}
