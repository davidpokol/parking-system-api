package com.davidpokolol.parkingsystemapi.model.response;

import com.davidpokolol.parkingsystemapi.model.enums.VehicleCategory;

public record VehicleResponse(
        Long id,
        String licensePlate,
        VehicleCategory vehicleCategory
) {
}
