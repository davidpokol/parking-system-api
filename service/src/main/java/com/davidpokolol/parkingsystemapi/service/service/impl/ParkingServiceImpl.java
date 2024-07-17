package com.davidpokolol.parkingsystemapi.service.service.impl;

import com.davidpokolol.parkingsystemapi.model.Parking;
import com.davidpokolol.parkingsystemapi.model.exception.EntityNotFoundException;
import com.davidpokolol.parkingsystemapi.repository.ParkingRepository;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingDTO;
import com.davidpokolol.parkingsystemapi.service.model.exception.InvalidParkingDtoException;
import com.davidpokolol.parkingsystemapi.service.service.ParkingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.davidpokolol.parkingsystemapi.service.constant.GlobalConstants.INVALID_PARAMETER_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.ParkingConstants.GET_ALL_PARKING_RECORDS_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.ParkingConstants.GET_PARKING_RECORD_BY_ID_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.ParkingConstants.PARKING_RECORD_NOT_FOUND_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.CREATE_VEHICLE_TEXT;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepository parkingRepository;
    private final Converter<ParkingDTO, Parking> parkingDtoToEntityConverter;
    private final Converter<Parking, ParkingDTO> parkingEntityToDtoConverter;


    @Override

    public List<ParkingDTO> getAllParkingRecords() {

        log.info(GET_ALL_PARKING_RECORDS_TEXT);
        return parkingRepository.findAll().stream()
                .map(parkingEntityToDtoConverter::convert)
                .toList();
    }

    @Override
    public Optional<ParkingDTO> getParkingRecord(Long id) {

        log.info(GET_PARKING_RECORD_BY_ID_TEXT, id);
        return Optional.ofNullable(parkingRepository.findById(id)
                .map(parkingEntityToDtoConverter::convert)
                .orElseThrow(() -> new EntityNotFoundException(
                        PARKING_RECORD_NOT_FOUND_TEXT + id
                ))
        );
    }

    @Override
    public ParkingDTO createParkingRecord(ParkingDTO parkingRecord) {

        log.info(CREATE_VEHICLE_TEXT, parkingRecord);

        return Optional.ofNullable(parkingRecord)
                .map(parkingDtoToEntityConverter::convert)
                .map(parkingRepository::save)
                .map(parkingEntityToDtoConverter::convert)
                .orElseThrow(() -> new IllegalArgumentException(
                        INVALID_PARAMETER_TEXT + parkingRecord
                ));
    }
}
