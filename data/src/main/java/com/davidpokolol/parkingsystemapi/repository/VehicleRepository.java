package com.davidpokolol.parkingsystemapi.repository;

import com.davidpokolol.parkingsystemapi.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByLicensePlateIgnoreCase(String licensePlate);
}
