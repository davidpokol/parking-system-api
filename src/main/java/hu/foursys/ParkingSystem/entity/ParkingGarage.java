package hu.foursys.ParkingSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "parking_garages")
public class ParkingGarage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", unique = true, nullable = false)
    @NotBlank
    private String address;

    @Column(name = "parking_spaces", nullable = false)
    @NotNull
    @Min(value = 1)
    private Integer numberOfParkingSpaces;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parkingGarage")
    private List<ParkingData> garageParkingData;

}
