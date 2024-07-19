package com.davidpokolol.parkingsystemapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "parkings")
@Entity
public class Parking {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "parkings_vehicles",
            joinColumns = @JoinColumn(name = "parking_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id", nullable = false)
    )
    private List<Vehicle> vehicles;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "parking_garage_id", nullable = false)
    private ParkingGarage parkingGarage;

    @NotNull
    @Past
    @Column(name = "start-time", nullable = false)
    private LocalDateTime startTime;

    @NotNull
    @PastOrPresent
    @Column(name = "end-time", nullable = false)
    private LocalDateTime endTime;
}