package com.davidpokolol.parkingsystemapi.service.model.dto;

public record ParkingGarageDTO(
        Long id,
        String address,
        int parkingSpaces
) {
}
