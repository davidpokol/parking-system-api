package com.davidpokolol.parkingsystemapi.service.service.impl;

import com.davidpokolol.parkingsystemapi.model.Vehicle;
import com.davidpokolol.parkingsystemapi.model.exception.EntityNotFoundException;
import com.davidpokolol.parkingsystemapi.repository.VehicleRepository;
import com.davidpokolol.parkingsystemapi.service.model.dto.VehicleDTO;
import com.davidpokolol.parkingsystemapi.service.model.exception.NotFoundException;
import com.davidpokolol.parkingsystemapi.service.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final Converter<VehicleDTO, Vehicle> vehicleDtoToEntityConverter;
    private final Converter<Vehicle, VehicleDTO> vehicleEntityToDtoConverter;

    @Override
    public List<VehicleDTO> getAllVehicles() {
        log.info("Get all vehicles.");
        return vehicleRepository.findAll().stream()
                .map(vehicleEntityToDtoConverter::convert)
                .toList();
    }

    @Override
    public Optional<VehicleDTO> getVehicle(final Long id) {
        log.info("Get a Vehicle with ID:{}", id);
        return Optional.ofNullable(vehicleRepository.findById(id)
                .map(vehicleEntityToDtoConverter::convert)
                .orElseThrow(() -> new NotFoundException("There is no Product with ID:" + id)));
    }

    @Override
    public VehicleDTO createVehicle(final VehicleDTO vehicle) {

        log.info("Creating vehicle: {}", vehicle);
        return Optional.ofNullable(vehicle)
                .map(vehicleDtoToEntityConverter::convert)
                .map(vehicleRepository::save)
                .map(vehicleEntityToDtoConverter::convert)
                .orElseThrow(() -> new IllegalArgumentException("Provided parameter is invalid: " + vehicle));
    }

    @Override
    public VehicleDTO updateVehicle(final Long id, final VehicleDTO vehicle) {

        log.info("Updating vehicle with ID:{} to: {}", id, vehicle);
        vehicleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Vehicle with id %d not found", id)
        ));
        return Optional.ofNullable(vehicle)
                .map(vehicleDtoToEntityConverter::convert)
                .map(v -> {
                    v.setId(id);
                    return v;
                })
                .map(vehicleRepository::save)
                .map(vehicleEntityToDtoConverter::convert)
                .orElseThrow(() -> new IllegalArgumentException("Provided parameter is invalid: " + vehicle));
    }

    @Override
    public void deleteVehicle(final Long id) {

        log.info("Deleting Vehicle with ID:{}.", id);
        vehicleRepository.deleteById(id);
    }
}