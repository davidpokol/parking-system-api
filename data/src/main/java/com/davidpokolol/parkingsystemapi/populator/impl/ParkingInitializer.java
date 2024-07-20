package com.davidpokolol.parkingsystemapi.populator.impl;

import com.davidpokolol.parkingsystemapi.model.Parking;
import com.davidpokolol.parkingsystemapi.model.ParkingGarage;
import com.davidpokolol.parkingsystemapi.model.Vehicle;
import com.davidpokolol.parkingsystemapi.populator.DatabasePopulator;
import com.davidpokolol.parkingsystemapi.repository.ParkingGarageRepository;
import com.davidpokolol.parkingsystemapi.repository.ParkingRepository;
import com.davidpokolol.parkingsystemapi.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Order(3)
@Component
public class ParkingInitializer implements DatabasePopulator {

    private final ParkingRepository parkingRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingGarageRepository parkingGarageRepository;

    @Override
    public void populate() {

        log.info("Initialize Parkings...");
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<ParkingGarage> parkingGarages = parkingGarageRepository.findAll();

        final List<Parking> PARKINGS = List.of(
                new Parking(1L,
                        List.of(vehicles.get(0)),
                        parkingGarages.get(0),
                        LocalDateTime.of(2022, 12, 3, 11, 40),
                        LocalDateTime.of(2022, 12, 3, 12, 15)
                ),
                new Parking(2L,
                        List.of(vehicles.get(1)),
                        parkingGarages.get(1),
                        LocalDateTime.of(2023, 2, 1, 6, 10),
                        LocalDateTime.of(2023, 3, 13, 18, 3)
                ),
                new Parking(3L,
                        List.of(vehicles.get(2)),
                        parkingGarages.get(2),
                        LocalDateTime.of(2024, 6, 23, 7, 30),
                        LocalDateTime.of(2024, 6, 23, 8, 23)
                )
        );
        parkingRepository.saveAll(PARKINGS);
        log.info("Finished initialization of Parkings.");

    }
}