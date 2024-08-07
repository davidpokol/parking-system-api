package com.davidpokolol.parkingsystemapi.service.impl;

import com.davidpokolol.parkingsystemapi.model.Parking;
import com.davidpokolol.parkingsystemapi.model.exception.EntityNotFoundException;
import com.davidpokolol.parkingsystemapi.repository.ParkingRepository;
import com.davidpokolol.parkingsystemapi.model.dto.ParkingDTO;
import com.davidpokolol.parkingsystemapi.service.ParkingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.davidpokolol.parkingsystemapi.constant.GlobalConstants.INVALID_PARAMETER_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.ParkingConstants.GET_ALL_PARKING_RECORDS_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.ParkingConstants.GET_PARKING_RECORD_BY_ID_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.ParkingConstants.PARKING_RECORD_NOT_FOUND_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.VehicleConstants.CREATE_VEHICLE_TEXT;

@Slf4j
@RequiredArgsConstructor
@Service
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
    public Optional<ParkingDTO> getParkingRecord(final Long id) {

        log.info(GET_PARKING_RECORD_BY_ID_TEXT, id);
        return Optional.ofNullable(parkingRepository.findById(id)
                .map(parkingEntityToDtoConverter::convert)
                .orElseThrow(() -> new EntityNotFoundException(
                        PARKING_RECORD_NOT_FOUND_TEXT + id
                ))
        );
    }

    @Override
    public ParkingDTO createParkingRecord(final ParkingDTO parkingRecord) {

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
