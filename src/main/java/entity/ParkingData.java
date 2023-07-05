package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class ParkingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @NotBlank
    private final ParkingGarage parkingGarage;

    @NotBlank
    private final Car car;

    @NotBlank
    private final LocalDateTime startTime;

    @NotBlank
    private final LocalDateTime endTime;

}
