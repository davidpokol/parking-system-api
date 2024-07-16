package com.davidpokolol.parkingsystemapi.contoller;

import com.davidpokolol.parkingsystemapi.model.exception.InvalidVehicleRequestException;
import com.davidpokolol.parkingsystemapi.model.response.VehicleResponse;
import com.davidpokolol.parkingsystemapi.service.model.dto.VehicleDTO;
import com.davidpokolol.parkingsystemapi.service.service.VehicleService;
import com.davidpokolol.parkingsystemapi.util.RequestValidationHandlerUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.CREATE_VEHICLE_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.DELETE_VEHICLE_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.GET_ALL_VEHICLES_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.GET_VEHICLE_BY_ID_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.UPDATE_VEHICLE_TEXT;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
@Slf4j
public class VehicleController {

    private final VehicleService vehicleService;
    private final Converter<VehicleDTO, VehicleResponse> vehicleDtoToResponseConverter;

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {

        log.info(GET_ALL_VEHICLES_TEXT);
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

        log.info(GET_VEHICLE_BY_ID_TEXT, id);
        Optional<VehicleDTO> vehicle = vehicleService.getVehicle(id);
        return vehicle.map(vehicleDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VehicleResponse> addVehicle(@Valid @RequestBody final VehicleDTO vehicle,
                                                      BindingResult bindingResult) throws InvalidVehicleRequestException {

        log.info(CREATE_VEHICLE_TEXT, vehicle);
        RequestValidationHandlerUtil.checkForVehicleRequestErrors(bindingResult);
        return Optional.of(vehicleService.createVehicle(vehicle))
                .map(vehicleDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable final Long id,
                                                         @Valid @RequestBody final VehicleDTO vehicle,
                                                         BindingResult bindingResult) {

        log.info(UPDATE_VEHICLE_TEXT, id, vehicle);
        RequestValidationHandlerUtil.checkForVehicleRequestErrors(bindingResult);
        return Optional.of(vehicleService.updateVehicle(id, vehicle))
                .map(vehicleDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable final Long id) {

        log.info(DELETE_VEHICLE_TEXT, id);
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
