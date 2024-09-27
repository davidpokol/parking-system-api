package com.davidpokolol.parkingsystemapi.contoller;

import com.davidpokolol.parkingsystemapi.model.dto.VehicleDTO;
import com.davidpokolol.parkingsystemapi.model.exception.InvalidVehicleRequestException;
import com.davidpokolol.parkingsystemapi.model.response.VehicleResponse;
import com.davidpokolol.parkingsystemapi.service.VehicleService;
import com.davidpokolol.parkingsystemapi.util.RequestValidationHandlerUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import static com.davidpokolol.parkingsystemapi.constant.VehicleConstants.CREATE_VEHICLE_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.VehicleConstants.DELETE_VEHICLE_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.VehicleConstants.GET_ALL_VEHICLES_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.VehicleConstants.GET_VEHICLE_BY_ID_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.VehicleConstants.GET_VEHICLE_BY_LICENSE_PLATE_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.VehicleConstants.UPDATE_VEHICLE_TEXT;

@Slf4j
@Tag(name = "Vehicle", description = "Operations for managing vehicles.")
@RequiredArgsConstructor
@RequestMapping("/v1/vehicles")
@RestController
public class VehicleController {

    private final VehicleService vehicleService;
    private final Converter<VehicleDTO, VehicleResponse> vehicleDtoToResponseConverter;

    @Operation(summary = "Returns all vehicles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = VehicleResponse.class
                                    )
                            )
                    )}
            )
    })
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

    @Operation(summary = "Returns a vehicle by the given id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = VehicleResponse.class)
                    )}
            ),
            @ApiResponse(responseCode = "404", description = "Vehicle not found.",
                    content = {@Content(schema = @Schema(hidden = true))})
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable final Long id) {

        log.info(GET_VEHICLE_BY_ID_TEXT, id);
        Optional<VehicleDTO> vehicle = vehicleService.getVehicle(id);
        return vehicle.map(vehicleDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Returns a vehicle by the given license plate.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = VehicleResponse.class)
                    )}
            ),
            @ApiResponse(responseCode = "404", description = "Vehicle not found.",
                    content = {@Content(schema = @Schema(hidden = true))})
    })
    @GetMapping("/license-plate/{licensePlate}")
    public ResponseEntity<VehicleResponse> getVehicleByLicensePlate(@PathVariable final String licensePlate) {

        log.info(GET_VEHICLE_BY_LICENSE_PLATE_TEXT, licensePlate);
        Optional<VehicleDTO> vehicle = vehicleService.getVehicle(licensePlate);
        return vehicle.map(vehicleDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Adds a vehicle.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = VehicleResponse.class)
                    )}
            ),
            @ApiResponse(responseCode = "400", description = "Invalid Vehicle supplied.",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(responseCode = "409", description = "A Vehicle already exist with the given license plate.",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(responseCode = "422", description = "The request body is empty or not well-formed.",
                    content = {@Content(schema = @Schema(hidden = true))})
    })
    @PostMapping
    public ResponseEntity<VehicleResponse> addVehicle(@Valid @RequestBody final VehicleDTO vehicle,
                                                      BindingResult bindingResult) throws InvalidVehicleRequestException {

        log.info(CREATE_VEHICLE_TEXT, vehicle);
        checkForRequestErrors(bindingResult);
        return Optional.of(vehicleService.createVehicle(vehicle))
                .map(vehicleDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Updates a vehicle.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = VehicleResponse.class)
                    )}
            ),
            @ApiResponse(responseCode = "400", description = "Invalid Vehicle supplied.",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(responseCode = "409", description = "A Vehicle already exist with the given license plate.",
                    content = {@Content(schema = @Schema(hidden = true))})
    })
    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable final Long id,
                                                         @Valid @RequestBody final VehicleDTO vehicle,
                                                         BindingResult bindingResult) {

        log.info(UPDATE_VEHICLE_TEXT, id, vehicle);
        checkForRequestErrors(bindingResult);
        return Optional.of(vehicleService.updateVehicle(id, vehicle))
                .map(vehicleDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Deletes a vehicle.",
            description = "This operation deletes a Vehicle and all associated parking records.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation.",
                    content = {@Content(schema = @Schema(hidden = true))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable final Long id) {

        log.info(DELETE_VEHICLE_TEXT, id);
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    private void checkForRequestErrors(final BindingResult bindingResult) {
        List<String> errors = RequestValidationHandlerUtil.getRequestErrors(bindingResult);
        if (!errors.isEmpty()) {
            throw new InvalidVehicleRequestException("Invalid vehicle request", errors);
        }
    }
}