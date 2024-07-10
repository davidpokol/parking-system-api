package com.davidpokolol.parkingsystemapi.repository;

import com.davidpokolol.parkingsystemapi.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
