package hu.foursys.ParkingSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    private Long id;

    @Pattern(regexp = "^[A-Za-z]{1,3}-[A-Za-z]{1,2}-[0-9]{1,4}$")
    @NotBlank
    private String licensePlate;

    @NotBlank
    private String color;

    @NotBlank
    private String type;
}