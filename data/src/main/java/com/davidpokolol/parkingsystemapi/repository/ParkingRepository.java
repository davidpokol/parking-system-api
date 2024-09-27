package com.davidpokolol.parkingsystemapi.repository;

import com.davidpokolol.parkingsystemapi.model.Parking;
import com.davidpokolol.parkingsystemapi.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    @Query(value = "SELECT p FROM Parking p WHERE :vehicle MEMBER OF p.vehicles")
    List<Parking> findAllByVehiclesContaining(@Param("vehicle") Vehicle vehicle);

    @Query(value = "SELECT p FROM Parking p WHERE p.id = :id")
    List<Parking> findAllByParkingGarageId(@Param("id") Long id);
}