package hu.foursys.ParkingSystem.service.impl;

import hu.foursys.ParkingSystem.dto.ParkingGarageDTO;
import hu.foursys.ParkingSystem.entity.ParkingGarage;
import hu.foursys.ParkingSystem.exception.DatabaseConstraintViolationException;
import hu.foursys.ParkingSystem.exception.ParkingGarageNotFoundException;
import hu.foursys.ParkingSystem.repository.GarageRepository;
import hu.foursys.ParkingSystem.service.GarageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GarageServiceImpl implements GarageService {

    private final GarageRepository garageRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ParkingGarageDTO> findAll() {
        return garageRepository.findAll()
                .stream()
                .map(garage -> modelMapper.map(garage, ParkingGarageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ParkingGarageDTO> findById(Long id) {
        return garageRepository.findById(id)
                .map(garage -> modelMapper.map(garage, ParkingGarageDTO.class));
    }

    @Override
    public ParkingGarageDTO create(ParkingGarageDTO parkingGarageDTO) {
        parkingGarageDTO.setId(null);

        ParkingGarage garageToSave = modelMapper.map(parkingGarageDTO, ParkingGarage.class);
        ParkingGarage savedGarage;
        try {
            savedGarage = garageRepository.save(garageToSave);
        } catch (Exception e) {
            throw new DatabaseConstraintViolationException(e.getMessage());
        }

        return modelMapper.map(savedGarage, ParkingGarageDTO.class);
    }

    @Override
    public ParkingGarageDTO update(ParkingGarageDTO parkingGarageDTO) {
        Long id = parkingGarageDTO.getId();

        garageRepository.findById(id)
                .orElseThrow(() -> new ParkingGarageNotFoundException(
                        String.format("Garage not found with id=%d", id)
                ));

        ParkingGarage garageToPersist = modelMapper.map(parkingGarageDTO, ParkingGarage.class);
        ParkingGarage savedGarage;
        try {
            savedGarage = garageRepository.save(garageToPersist);
        } catch (Exception e) {
            throw new DatabaseConstraintViolationException(e.getMessage());
        }

        return modelMapper.map(savedGarage, ParkingGarageDTO.class);
    }

    @Override
    public void delete(Long id) {
        ParkingGarage garageToDelete = garageRepository.findById(id)
                .orElseThrow(() -> new ParkingGarageNotFoundException(
                        String.format("Parking garage not found with id=%d", id)
                ));
        try {
            garageRepository.delete(garageToDelete);
        } catch (Exception e) {
            throw new DatabaseConstraintViolationException(e.getMessage());
        }
    }
}
