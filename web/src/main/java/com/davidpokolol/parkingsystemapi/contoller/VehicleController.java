package com.davidpokolol.parkingsystemapi.contoller;

import com.davidpokolol.parkingsystemapi.model.response.VehicleResponse;
import com.davidpokolol.parkingsystemapi.service.model.dto.VehicleDTO;
import com.davidpokolol.parkingsystemapi.service.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
@Slf4j
public class VehicleController {

    private final VehicleService vehicleService;
    private final Converter<VehicleDTO, VehicleResponse> vehicleDtoToResponseConverter;

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {

        log.info("Getting all vehicles.");
        List<VehicleResponse> vehicles =
                vehicleService
                        .getAllVehicles()
                        .stream()
                        .map(vehicleDtoToResponseConverter::convert)
                        .toList();

        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable final Long id) {

        log.info("Getting a vehicle with ID: {}", id);
        Optional<VehicleDTO> vehicle = vehicleService.getVehicle(id);
        return vehicle.map(vehicleDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Vehicle not found with ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<VehicleResponse> addVehicle(@RequestBody final VehicleDTO vehicle) {

        log.info("Creating a vehicle: {}", vehicle);
        return Optional.of(vehicleService.createVehicle(vehicle))
                .map(vehicleDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable final Long id,
                                                         @RequestBody final VehicleDTO vehicle) {

        log.info("Updating a vehicle with ID:{} to: {}", id, vehicle);
        return Optional.of(vehicleService.updateVehicle(id, vehicle))
                .map(vehicleDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable final Long id) {

        log.info("Deleting a vehicle with ID: {}", id);
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
