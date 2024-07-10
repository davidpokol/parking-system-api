package com.davidpokolol.parkingsystemapi.repository;

import com.davidpokolol.parkingsystemapi.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
}
