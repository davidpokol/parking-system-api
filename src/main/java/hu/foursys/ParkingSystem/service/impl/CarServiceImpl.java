package hu.foursys.ParkingSystem.service.impl;

import hu.foursys.ParkingSystem.dto.CarDTO;
import hu.foursys.ParkingSystem.entity.Car;
import hu.foursys.ParkingSystem.exception.CarNotFoundException;
import hu.foursys.ParkingSystem.exception.DatabaseConstraintViolationException;
import hu.foursys.ParkingSystem.repository.CarRepository;
import hu.foursys.ParkingSystem.service.CarService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CarDTO> findAll() {
        return carRepository.findAll()
                .stream()
                .map(car -> modelMapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CarDTO> findById(Long id) {
        return carRepository.findById(id)
                .map(car -> modelMapper.map(car, CarDTO.class));
    }

    @Override
    public CarDTO create(CarDTO carDTO) {
        carDTO.setId(null);

        Car carToSave = modelMapper.map(carDTO, Car.class);
        Car savedCar;
        try {
            savedCar = carRepository.save(carToSave);
        } catch (Exception e) {
            throw new DatabaseConstraintViolationException(e.getMessage());
        }
        return modelMapper.map(savedCar, CarDTO.class);
    }

    @Override
    public CarDTO update(CarDTO carDTO) {
        Long id = carDTO.getId();
        carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(
                        String.format("Car not found with id=%d", id)
                ));

        Car carToPersist = modelMapper.map(carDTO, Car.class);
        Car savedCar;
        try {
            savedCar = carRepository.save(carToPersist);
        } catch (Exception e) {
            throw new DatabaseConstraintViolationException(e.getMessage());
        }
        return modelMapper.map(savedCar, CarDTO.class);
    }

    @Override
    public void delete(Long id) {
        Car carToDelete = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(String.format("Car not found with id=%d", id)));
        try {
            carRepository.delete(carToDelete);
        } catch (Exception e) {
            throw new DatabaseConstraintViolationException(e.getMessage());
        }
    }

}
