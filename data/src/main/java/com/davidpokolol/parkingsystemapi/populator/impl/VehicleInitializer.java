package com.davidpokolol.parkingsystemapi.populator.impl;

import com.davidpokolol.parkingsystemapi.model.Vehicle;
import com.davidpokolol.parkingsystemapi.model.enums.VehicleCategory;
import com.davidpokolol.parkingsystemapi.populator.DatabasePopulator;
import com.davidpokolol.parkingsystemapi.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Order(1)
@Component
public class VehicleInitializer implements DatabasePopulator {

    private static final List<Vehicle> VEHICLES = List.of(
            new Vehicle(1L, "RRC-781", VehicleCategory.M1),
            new Vehicle(2L, "GUT-145", VehicleCategory.N1),
            new Vehicle(3L, "AUDI-888", VehicleCategory.T)
    );

    private final VehicleRepository vehicleRepository;

    @Override
    public void populate() {

        log.info("Initialize Vehicles...");
        vehicleRepository.saveAll(VEHICLES);
        log.info("Finished initialization of Vehicles.");

    }
}