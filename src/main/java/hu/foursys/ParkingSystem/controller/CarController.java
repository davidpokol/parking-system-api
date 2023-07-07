package hu.foursys.ParkingSystem.controller;

import hu.foursys.ParkingSystem.dto.CarDTO;
import hu.foursys.ParkingSystem.exception.CarNotFoundException;
import hu.foursys.ParkingSystem.exception.DatabaseConstraintViolationException;
import hu.foursys.ParkingSystem.exception.InvalidRequestException;
import hu.foursys.ParkingSystem.service.CarService;
import hu.foursys.ParkingSystem.util.RequestUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/car")
@Slf4j
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final RequestUtil requestUtil;

    @GetMapping
    public ResponseEntity<List<CarDTO>> findAll() {
        List<CarDTO> cars = carService.findAll();
        return ResponseEntity.ok().body(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> findById(@PathVariable Long id) {
        Optional<CarDTO> car = carService.findById(id);
        return car.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<CarDTO> create(@Valid @RequestBody CarDTO carDTO, BindingResult bindingResult)
            throws InvalidRequestException, DatabaseConstraintViolationException {
        requestUtil.checkForRequestErrors(bindingResult);

        CarDTO savedCar = carService.create(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCar);
    }

    @PutMapping("/update")
    public ResponseEntity<CarDTO> update(@Valid @RequestBody CarDTO carDTO, BindingResult bindingResult)
            throws InvalidRequestException, CarNotFoundException, DatabaseConstraintViolationException {

        if (Objects.isNull(carDTO.getId())) {
            bindingResult.rejectValue("id","","must not be null");
        }
        requestUtil.checkForRequestErrors(bindingResult);

        CarDTO updatedCar = carService.update(carDTO);
        return ResponseEntity.ok()
                .body(updatedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
            throws CarNotFoundException {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
