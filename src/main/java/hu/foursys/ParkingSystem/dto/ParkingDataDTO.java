package hu.foursys.ParkingSystem.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingDataDTO {

    private Long id;

    @NotBlank
    private String parkingGarageAddress;

    @NotBlank
    private String carLicensePlate;

    @Past
    @NotNull
    private LocalDateTime startTime;

    @PastOrPresent
    @NotNull
    private LocalDateTime endTime;
}
