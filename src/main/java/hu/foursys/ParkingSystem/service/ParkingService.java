package hu.foursys.ParkingSystem.service;

import hu.foursys.ParkingSystem.dto.ParkingDataDTO;
import hu.foursys.ParkingSystem.response.ParkingDataResponse;

import java.util.List;
import java.util.Optional;

public interface ParkingService {
    List<ParkingDataResponse> findAll();
    ParkingDataResponse create(ParkingDataDTO parkingDataDTO);
}
