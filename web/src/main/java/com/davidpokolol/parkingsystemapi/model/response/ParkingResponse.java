package com.davidpokolol.parkingsystemapi.model.response;

import java.time.LocalDateTime;

public record ParkingResponse(
        Long id,
        VehicleResponse vehicle,
        ParkingGarageResponse parkingGarage,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
