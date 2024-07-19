package com.davidpokolol.parkingsystemapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "parking-garages")
@Entity
public class ParkingGarage {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(min = 10, max = 60)
    @Column(nullable = false, unique = true, length = 60)
    private String address;

    @Range(min = 10, max = 1_000)
    @Column(name = "parking_spaces", nullable = false)
    private int parkingSpaces;
}