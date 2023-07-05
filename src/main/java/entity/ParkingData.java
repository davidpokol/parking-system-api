package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parkings")
public class ParkingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "garage_id", nullable = false)
    @NotBlank
    private ParkingGarage parkingGarage;

    @Column(name = "car_id", nullable = false)
    @NotBlank
    private Car car;

    @Column(name = "start_time", nullable = false)
    @NotNull
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    @NotNull
    private  LocalDateTime endTime;

}
