package com.davidpokolol.parkingsystemapi.service;

import com.davidpokolol.parkingsystemapi.model.dto.ParkingDTO;

import java.util.List;
import java.util.Optional;

public interface ParkingService {

    List<ParkingDTO> getAllParkingRecords();

    Optional<ParkingDTO> getParkingRecord(final Long id);

    ParkingDTO createParkingRecord(final ParkingDTO parkingRecord);
}
