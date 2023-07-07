package hu.foursys.ParkingSystem.repository;

import hu.foursys.ParkingSystem.entity.Car;
import hu.foursys.ParkingSystem.entity.ParkingGarage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GarageRepository extends JpaRepository<ParkingGarage, Long> {

    Optional<ParkingGarage> findByAddress(String address);
}
