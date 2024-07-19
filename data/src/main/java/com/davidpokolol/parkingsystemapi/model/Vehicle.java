package com.davidpokolol.parkingsystemapi.model;

import com.davidpokolol.parkingsystemapi.model.enums.VehicleCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "vehicles")
@Entity
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 5, max = 10)
    @Column(name = "license_plate", nullable = false, unique = true, length = 10)
    private String licensePlate;

    @NotNull
    @Column(name = "vehicle_category", nullable = false)
    private VehicleCategory vehicleCategory;
}