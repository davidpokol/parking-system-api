package com.davidpokolol.parkingsystemapi.service.service.impl;

import com.davidpokolol.parkingsystemapi.model.ParkingGarage;
import com.davidpokolol.parkingsystemapi.model.exception.EntityNotFoundException;
import com.davidpokolol.parkingsystemapi.repository.ParkingGarageRepository;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingGarageDTO;
import com.davidpokolol.parkingsystemapi.service.service.ParkingGarageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingGarageServiceImpl implements ParkingGarageService {


    public static final String PARKING_GARAGE_NOT_FOUND = "There is no parking garage with ID:";

    private final ParkingGarageRepository parkingGarageRepository;
    private final Converter<ParkingGarageDTO, ParkingGarage> parkingGarageDtoToEntityConverter;
    private final Converter<ParkingGarage, ParkingGarageDTO> parkingGarageEntityToDtoConverter;

    @Override
    public List<ParkingGarageDTO> getAllParkingGarages() {

        log.info("Getting all parking garages.");
        return parkingGarageRepository.findAll().stream()
                .map(parkingGarageEntityToDtoConverter::convert)
                .toList();
    }

    @Override
    public Optional<ParkingGarageDTO> getParkingGarage(Long id) {

        log.info("Getting a parking garage with ID:{}", id);
        return Optional.ofNullable(parkingGarageRepository.findById(id)
                .map(parkingGarageEntityToDtoConverter::convert)
                .orElseThrow(() -> new EntityNotFoundException(
                        PARKING_GARAGE_NOT_FOUND + id
                ))
        );
    }

    @Override
    public ParkingGarageDTO createParkingGarage(ParkingGarageDTO parkingGarage) {

        log.info("Creating a parking garage: {}", parkingGarage);
        return Optional.ofNullable(parkingGarage)
                .map(parkingGarageDtoToEntityConverter::convert)
                .map(parkingGarageRepository::save)
                .map(parkingGarageEntityToDtoConverter::convert)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Provided parameter is invalid: " + parkingGarage
                ));
    }

    @Override
    public ParkingGarageDTO updateParkingGarage(Long id, ParkingGarageDTO parkingGarage) {

        log.info("Updating a parking garage with ID:{} to: {}", id, parkingGarage);
        parkingGarageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        PARKING_GARAGE_NOT_FOUND + id
                ));

        return Optional.ofNullable(parkingGarage)
                .map(parkingGarageDtoToEntityConverter::convert)
                .map(pg -> {
                    pg.setId(id);
                    return pg;
                })
                .map(parkingGarageRepository::save)
                .map(parkingGarageEntityToDtoConverter::convert)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Provided parameter is invalid: " + parkingGarage
                ));
    }

    @Override
    public void deleteParkingGarage(Long id) {

        log.info("Deleting a parking garage with ID:{}.", id);
        parkingGarageRepository.deleteById(id);
    }
}
