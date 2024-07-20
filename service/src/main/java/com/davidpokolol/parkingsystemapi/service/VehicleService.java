package com.davidpokolol.parkingsystemapi.service;

import com.davidpokolol.parkingsystemapi.model.dto.VehicleDTO;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<VehicleDTO> getAllVehicles();

    Optional<VehicleDTO> getVehicle(final Long id);

    Optional<VehicleDTO> getVehicle(final String licensePlate);

    VehicleDTO createVehicle(final VehicleDTO vehicle);

    VehicleDTO updateVehicle(final Long id, final VehicleDTO vehicle);

    void deleteVehicle(final Long id);
}
