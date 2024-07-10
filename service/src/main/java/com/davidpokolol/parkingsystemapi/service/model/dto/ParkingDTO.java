package com.davidpokolol.parkingsystemapi.service.model.dto;

import java.time.LocalDateTime;

public record ParkingDTO(
        Long id,
        VehicleDTO vehicle,
        ParkingGarageDTO parkingGarage,
        LocalDateTime startTime,
        LocalDateTime endTime
) {

}
