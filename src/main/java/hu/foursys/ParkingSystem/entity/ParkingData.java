package hu.foursys.ParkingSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parkings")
public class ParkingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garage_id", nullable = false)
    @NotNull
    private ParkingGarage parkingGarage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    @NotNull
    private Car car;

    @Column(name = "start_time", nullable = false)
    @Past
    @NotNull
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    @PastOrPresent
    @NotNull
    private LocalDateTime endTime;

}
