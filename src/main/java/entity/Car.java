package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-Za-z]{1,3}-[A-Za-z]{1,2}-[0-9]{1,4}$")
    @NotBlank
    private String licensePlate;

    @NotBlank
    private String color;

    @NotBlank
    private String type;
}
