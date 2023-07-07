package hu.foursys.ParkingSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate", unique = true, nullable = false)
    @Pattern(regexp = "^[A-Za-z]{1,3}-[A-Za-z]{1,2}-[0-9]{1,4}$")
    @NotBlank
    private String licensePlate;

    @Column(name = "color", nullable = false)
    @NotBlank
    private String color;

    @Column(name = "type", nullable = false)
    @NotBlank
    private String type;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car")
    private List<ParkingData> carParkingData;
}
