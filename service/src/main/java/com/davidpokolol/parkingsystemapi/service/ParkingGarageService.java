package com.davidpokolol.parkingsystemapi.service;

import com.davidpokolol.parkingsystemapi.model.dto.ParkingGarageDTO;

import java.util.List;
import java.util.Optional;

public interface ParkingGarageService {

    List<ParkingGarageDTO> getAllParkingGarages();

    Optional<ParkingGarageDTO> getParkingGarage(final Long id);

    ParkingGarageDTO createParkingGarage(final ParkingGarageDTO parkingGarage);

    ParkingGarageDTO updateParkingGarage(final Long id, final ParkingGarageDTO parkingGarage);

    void deleteParkingGarage(final Long id);
}
