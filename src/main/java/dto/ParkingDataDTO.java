package dto;

import entity.Car;
import entity.ParkingGarage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ParkingDataDTO {

    private Long id;

    @NotBlank
    private Long parkingGarageId;

    @NotBlank
    private String carLicensePlate;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private  LocalDateTime endTime;
}
