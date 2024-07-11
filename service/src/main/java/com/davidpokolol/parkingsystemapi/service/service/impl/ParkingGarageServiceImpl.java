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

import static com.davidpokolol.parkingsystemapi.service.constant.GlobalConstants.INVALID_PARAMETER_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.ParkingGarageConstants.CREATE_PARKING_GARAGE_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.ParkingGarageConstants.DELETE_PARKING_GARAGE_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.ParkingGarageConstants.GET_ALL_PARKING_GARAGES_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.ParkingGarageConstants.GET_PARKING_GARAGE_BY_ID_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.ParkingGarageConstants.PARKING_GARAGE_NOT_FOUND_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.ParkingGarageConstants.UPDATE_PARKING_GARAGE_TEXT;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingGarageServiceImpl implements ParkingGarageService {

    private final ParkingGarageRepository parkingGarageRepository;
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
    public Optional<ParkingGarageDTO> getParkingGarage(Long id) {

        log.info(GET_PARKING_GARAGE_BY_ID_TEXT, id);
        return Optional.ofNullable(parkingGarageRepository.findById(id)
                .map(parkingGarageEntityToDtoConverter::convert)
                .orElseThrow(() -> new EntityNotFoundException(
                        PARKING_GARAGE_NOT_FOUND_TEXT + id
                ))
        );
    }

    @Override
    public ParkingGarageDTO createParkingGarage(ParkingGarageDTO parkingGarage) {

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
    public ParkingGarageDTO updateParkingGarage(Long id, ParkingGarageDTO parkingGarage) {

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
    public void deleteParkingGarage(Long id) {

        log.info(DELETE_PARKING_GARAGE_TEXT, id);
        parkingGarageRepository.deleteById(id);
    }
}
