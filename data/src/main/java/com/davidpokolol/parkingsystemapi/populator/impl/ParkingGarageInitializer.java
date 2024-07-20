package com.davidpokolol.parkingsystemapi.populator.impl;

import com.davidpokolol.parkingsystemapi.model.ParkingGarage;
import com.davidpokolol.parkingsystemapi.populator.DatabasePopulator;
import com.davidpokolol.parkingsystemapi.repository.ParkingGarageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Order(2)
@Component
public class ParkingGarageInitializer implements DatabasePopulator {

    private static final List<ParkingGarage> PARKING_GARAGES = List.of(

            new ParkingGarage(1L, "Nagyvárda, Kishatár utca 18.", 30),
            new ParkingGarage(2L, "Mucsaröcsöge, Fő utca 2.", 15),
            new ParkingGarage(3L, "Nagykanizsa, Kinizsi Pál utca 5.", 40)
    );

    private final ParkingGarageRepository parkingGarageRepository;

    @Override
    public void populate() {

        log.info("Initialize Parkings...");
        parkingGarageRepository.saveAll(PARKING_GARAGES);
        log.info("Finished initialization of Parkings.");

    }
}
