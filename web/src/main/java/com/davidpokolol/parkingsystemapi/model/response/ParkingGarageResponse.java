package com.davidpokolol.parkingsystemapi.model.response;

public record ParkingGarageResponse(
        Long id,
        String address,
        int parkingSpaces
) {
}
