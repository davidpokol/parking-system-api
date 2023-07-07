package hu.foursys.ParkingSystem.controller;

import hu.foursys.ParkingSystem.dto.ParkingGarageDTO;
import hu.foursys.ParkingSystem.exception.InvalidRequestException;
import hu.foursys.ParkingSystem.exception.ParkingGarageNotFoundException;
import hu.foursys.ParkingSystem.service.GarageService;
import hu.foursys.ParkingSystem.util.RequestUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/garage")
@Slf4j
@RequiredArgsConstructor
public class GarageController {

    private final GarageService garageService;
    private final RequestUtil requestUtil;

    @GetMapping
    public ResponseEntity<List<ParkingGarageDTO>> findAll() {
        List<ParkingGarageDTO> garages = garageService.findAll();
        return ResponseEntity.ok().body(garages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingGarageDTO> findById(@PathVariable Long id) throws ParkingGarageNotFoundException {
        Optional<ParkingGarageDTO> garage = garageService.findById(id);
        return garage.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<ParkingGarageDTO> create(
            @Valid @RequestBody ParkingGarageDTO parkingGarageDTO,
            BindingResult bindingResult)
            throws InvalidRequestException {
        requestUtil.checkForRequestErrors(bindingResult);

        ParkingGarageDTO savedGarage = garageService.create(parkingGarageDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedGarage);
    }

    @PutMapping("/update")
    public ResponseEntity<ParkingGarageDTO> update(
            @Valid @RequestBody ParkingGarageDTO parkingGarageDTO,
            BindingResult bindingResult)
            throws InvalidRequestException {

        if (Objects.isNull(parkingGarageDTO.getId())) {
            bindingResult.rejectValue("id","","must not be null");
        }
        requestUtil.checkForRequestErrors(bindingResult);

        ParkingGarageDTO updatedGarage = garageService.update(parkingGarageDTO);
        return ResponseEntity.ok()
                .body(updatedGarage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        garageService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
