package com.davidpokolol.parkingsystemapi.service.service.impl;

import com.davidpokolol.parkingsystemapi.model.Vehicle;
import com.davidpokolol.parkingsystemapi.model.exception.EntityNotFoundException;
import com.davidpokolol.parkingsystemapi.repository.ParkingRepository;
import com.davidpokolol.parkingsystemapi.repository.VehicleRepository;
import com.davidpokolol.parkingsystemapi.service.model.dto.VehicleDTO;
import com.davidpokolol.parkingsystemapi.service.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.davidpokolol.parkingsystemapi.service.constant.GlobalConstants.INVALID_PARAMETER_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.CREATE_VEHICLE_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.DELETE_VEHICLE_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.GET_ALL_VEHICLES_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.GET_VEHICLE_BY_ID_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.GET_VEHICLE_BY_LICENSE_PLATE_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.UPDATE_VEHICLE_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.VEHICLE_NOT_FOUND_WITH_ID_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.VEHICLE_NOT_FOUND_WITH_LICENSE_PLATE_TEXT;


@Slf4j
@RequiredArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ParkingRepository parkingRepository;
    private final Converter<VehicleDTO, Vehicle> vehicleDtoToEntityConverter;
    private final Converter<Vehicle, VehicleDTO> vehicleEntityToDtoConverter;

    @Override
    public List<VehicleDTO> getAllVehicles() {

        log.info(GET_ALL_VEHICLES_TEXT);
        return vehicleRepository.findAll().stream()
                .map(vehicleEntityToDtoConverter::convert)
                .toList();
    }

    @Override
    public Optional<VehicleDTO> getVehicle(final Long id) {

        log.info(GET_VEHICLE_BY_ID_TEXT, id);
        return Optional.ofNullable(vehicleRepository.findById(id)
                .map(vehicleEntityToDtoConverter::convert)
                .orElseThrow(() -> new EntityNotFoundException(
                        VEHICLE_NOT_FOUND_WITH_ID_TEXT + id
                ))
        );
    }

    @Override
    public Optional<VehicleDTO> getVehicle(final String licensePlate) {
        log.info(GET_VEHICLE_BY_LICENSE_PLATE_TEXT, licensePlate);
        return Optional.ofNullable(vehicleRepository.findByLicensePlateIgnoreCase(licensePlate)
                .map(vehicleEntityToDtoConverter::convert)
                .orElseThrow(() -> new EntityNotFoundException(
                        VEHICLE_NOT_FOUND_WITH_LICENSE_PLATE_TEXT + licensePlate
                ))
        );
    }

    @Override
    public VehicleDTO createVehicle(final VehicleDTO vehicle) {

        log.info(CREATE_VEHICLE_TEXT, vehicle);
        return Optional.ofNullable(vehicle)
                .map(vehicleDtoToEntityConverter::convert)
                .map(vehicleRepository::save)
                .map(vehicleEntityToDtoConverter::convert)
                .orElseThrow(() -> new IllegalArgumentException(
                        INVALID_PARAMETER_TEXT + vehicle
                ));
    }

    @Override
    public VehicleDTO updateVehicle(final Long id, final VehicleDTO vehicle) {

        log.info(UPDATE_VEHICLE_TEXT, id, vehicle);
        vehicleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                VEHICLE_NOT_FOUND_WITH_ID_TEXT + id
        ));
        return Optional.ofNullable(vehicle)
                .map(vehicleDtoToEntityConverter::convert)
                .map(v -> {
                    v.setId(id);
                    return v;
                })
                .map(vehicleRepository::save)
                .map(vehicleEntityToDtoConverter::convert)
                .orElseThrow(() -> new IllegalArgumentException(
                        INVALID_PARAMETER_TEXT + vehicle
                ));
    }

    @Override
    public void deleteVehicle(final Long id) {

        log.info(DELETE_VEHICLE_TEXT, id);
        vehicleRepository.findById(id).ifPresent(vehicle -> {
            parkingRepository.findAllByVehiclesContaining(vehicle).forEach(parking -> {
                parking.setVehicles(Collections.emptyList());
                parking.setParkingGarage(null);
                parkingRepository.deleteById(parkingRepository.save(parking).getId());
            });

            vehicleRepository.deleteById(id);
        });
    }
}