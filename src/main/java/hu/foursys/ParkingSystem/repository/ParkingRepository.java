package hu.foursys.ParkingSystem.repository;

import hu.foursys.ParkingSystem.entity.ParkingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingData, Long> {


}
