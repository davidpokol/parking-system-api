package hu.foursys.ParkingSystem.response;

import hu.foursys.ParkingSystem.dto.CarDTO;
import hu.foursys.ParkingSystem.dto.ParkingGarageDTO;
import hu.foursys.ParkingSystem.entity.Car;
import hu.foursys.ParkingSystem.entity.ParkingGarage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingDataResponse {

    @NotNull
    private Long id;

    @NotBlank
    private ParkingGarageDTO parkingGarage;

    @NotBlank
    private CarDTO car;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

}
