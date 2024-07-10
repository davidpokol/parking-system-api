package com.davidpokolol.parkingsystemapi.service.service;

import com.davidpokolol.parkingsystemapi.service.model.dto.VehicleDTO;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<VehicleDTO> getAllVehicles();

    Optional<VehicleDTO> getVehicle(final Long id);

    VehicleDTO createVehicle(final VehicleDTO vehicleDTO);

    VehicleDTO updateVehicle(final Long id, final VehicleDTO vehicleDTO);

    void deleteVehicle(final Long id);
}
