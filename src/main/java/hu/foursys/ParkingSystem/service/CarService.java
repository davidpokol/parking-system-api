package hu.foursys.ParkingSystem.service;

import hu.foursys.ParkingSystem.dto.CarDTO;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<CarDTO> findAll();

    Optional<CarDTO> findById(Long id);

    CarDTO create(CarDTO carDTO);

    CarDTO update(CarDTO carDTO);

    void delete(Long id);
}
