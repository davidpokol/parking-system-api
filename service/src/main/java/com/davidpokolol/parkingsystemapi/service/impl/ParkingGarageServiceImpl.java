package com.davidpokolol.parkingsystemapi.service.impl;

import com.davidpokolol.parkingsystemapi.model.ParkingGarage;
import com.davidpokolol.parkingsystemapi.model.dto.ParkingGarageDTO;
import com.davidpokolol.parkingsystemapi.model.exception.EntityNotFoundException;
import com.davidpokolol.parkingsystemapi.repository.ParkingGarageRepository;
import com.davidpokolol.parkingsystemapi.repository.ParkingRepository;
import com.davidpokolol.parkingsystemapi.service.ParkingGarageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.davidpokolol.parkingsystemapi.constant.GlobalConstants.INVALID_PARAMETER_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.ParkingGarageConstants.CREATE_PARKING_GARAGE_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.ParkingGarageConstants.DELETE_PARKING_GARAGE_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.ParkingGarageConstants.GET_ALL_PARKING_GARAGES_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.ParkingGarageConstants.GET_PARKING_GARAGE_BY_ID_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.ParkingGarageConstants.PARKING_GARAGE_NOT_FOUND_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.ParkingGarageConstants.UPDATE_PARKING_GARAGE_TEXT;

@Slf4j
@RequiredArgsConstructor
@Service
public class ParkingGarageServiceImpl implements ParkingGarageService {

    private final ParkingGarageRepository parkingGarageRepository;
    private final ParkingRepository parkingRepository;
    private final Converter<ParkingGarageDTO, ParkingGarage> parkingGarageDtoToEntityConverter;
    private final Converter<ParkingGarage, ParkingGarageDTO> parkingGarageEntityToDtoConverter;

    @Override
    public List<ParkingGarageDTO> getAllParkingGarages() {

        log.info(GET_ALL_PARKING_GARAGES_TEXT);
        return parkingGarageRepository.findAll().stream()
                .map(parkingGarageEntityToDtoConverter::convert)
                .toList();
    }

    @Override
    public Optional<ParkingGarageDTO> getParkingGarage(final Long id) {

        log.info(GET_PARKING_GARAGE_BY_ID_TEXT, id);
        return Optional.ofNullable(parkingGarageRepository.findById(id)
                .map(parkingGarageEntityToDtoConverter::convert)
                .orElseThrow(() -> new EntityNotFoundException(
                        PARKING_GARAGE_NOT_FOUND_TEXT + id
                ))
        );
    }

    @Override
    public ParkingGarageDTO createParkingGarage(final ParkingGarageDTO parkingGarage) {

        log.info(CREATE_PARKING_GARAGE_TEXT, parkingGarage);
        return Optional.ofNullable(parkingGarage)
                .map(parkingGarageDtoToEntityConverter::convert)
                .map(parkingGarageRepository::save)
                .map(parkingGarageEntityToDtoConverter::convert)
                .orElseThrow(() -> new IllegalArgumentException(
                        INVALID_PARAMETER_TEXT + parkingGarage
                ));
    }

    @Override
    public ParkingGarageDTO updateParkingGarage(final Long id, final ParkingGarageDTO parkingGarage) {

        log.info(UPDATE_PARKING_GARAGE_TEXT, id, parkingGarage);
        parkingGarageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        PARKING_GARAGE_NOT_FOUND_TEXT + id
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
                        INVALID_PARAMETER_TEXT + parkingGarage
                ));
    }

    @Override
    public void deleteParkingGarage(final Long id) {

        log.info(DELETE_PARKING_GARAGE_TEXT, id);
        parkingGarageRepository.findById(id).ifPresent(parkingGarage -> {
            parkingRepository.findAllByParkingGarageId(parkingGarage.getId()).forEach(parking -> {
                parking.setVehicles(Collections.emptyList());
                parking.setParkingGarage(null);
                parkingRepository.deleteById(parkingRepository.save(parking).getId());
            });

            parkingGarageRepository.deleteById(id);
        });
    }
}