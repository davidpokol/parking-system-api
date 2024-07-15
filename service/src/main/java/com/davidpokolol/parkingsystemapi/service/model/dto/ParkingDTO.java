package com.davidpokolol.parkingsystemapi.service.model.dto;

import java.time.LocalDateTime;

public record ParkingDTO(
        Long id,
        String vehicleLicensePlate,
        Long parkingGarageId,
        LocalDateTime startTime,
        LocalDateTime endTime
) {

}
