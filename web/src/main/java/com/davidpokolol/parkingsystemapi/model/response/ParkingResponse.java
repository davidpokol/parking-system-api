package com.davidpokolol.parkingsystemapi.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

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
