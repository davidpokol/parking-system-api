package hu.foursys.ParkingSystem.service;

import hu.foursys.ParkingSystem.dto.ParkingGarageDTO;

import java.util.List;
import java.util.Optional;

public interface GarageService {

    List<ParkingGarageDTO> findAll();

    Optional<ParkingGarageDTO> findById(Long id);

    ParkingGarageDTO create(ParkingGarageDTO parkingGarageDTO);

    ParkingGarageDTO update(ParkingGarageDTO parkingGarageDTO);

    void delete(Long id);
}
