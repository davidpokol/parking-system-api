package com.davidpokolol.parkingsystemapi.contoller;

import com.davidpokolol.parkingsystemapi.model.Vehicle;
import com.davidpokolol.parkingsystemapi.model.response.VehicleResponse;
import com.davidpokolol.parkingsystemapi.service.model.dto.VehicleDTO;
import com.davidpokolol.parkingsystemapi.service.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
@Slf4j
public class VehicleController {

    private final VehicleService vehicleService;
    private final Converter<VehicleDTO, VehicleResponse> vehicleDtoToResponseConverter;
    private final Converter<VehicleDTO, Vehicle> vehicleDtoToEntityConverter;

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> findAll() {

        log.info("Getting all vehicles.");
        List<VehicleResponse> vehicles =
                vehicleService
                        .getAllVehicles()
                        .stream()
                        .map(vehicleDtoToResponseConverter::convert)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> findById(@PathVariable final Long id) {

        log.info("Getting vehicle with ID: {}", id);
        Optional<VehicleDTO> vehicle = vehicleService.getVehicle(id);
        return vehicle.map(vehicleDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("No vehicle found with ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<VehicleResponse> addVehicle(@RequestBody final VehicleDTO vehicleDTO) {

        log.info("Creating vehicle: {}", vehicleDTO);
        return Optional.of(vehicleService.createVehicle(vehicleDTO))
                .map(vehicleDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
