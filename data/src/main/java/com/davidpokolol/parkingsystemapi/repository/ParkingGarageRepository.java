package com.davidpokolol.parkingsystemapi.repository;

import com.davidpokolol.parkingsystemapi.model.ParkingGarage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingGarageRepository extends JpaRepository<ParkingGarage, Long> {
}
