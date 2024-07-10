package com.davidpokolol.parkingsystemapi.service.model.dto;

import com.davidpokolol.parkingsystemapi.model.enums.VehicleCategory;

public record VehicleDTO(
        Long id,
        String licensePlate,
        VehicleCategory vehicleCategory
) {
}
