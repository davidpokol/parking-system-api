package com.davidpokolol.parkingsystemapi.contoller;

import com.davidpokolol.parkingsystemapi.model.dto.ParkingGarageDTO;
import com.davidpokolol.parkingsystemapi.model.exception.InvalidParkingGarageRequestException;
import com.davidpokolol.parkingsystemapi.model.response.ParkingGarageResponse;
import com.davidpokolol.parkingsystemapi.service.ParkingGarageService;
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

import static com.davidpokolol.parkingsystemapi.constant.ParkingGarageConstants.CREATE_PARKING_GARAGE_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.ParkingGarageConstants.DELETE_PARKING_GARAGE_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.ParkingGarageConstants.GET_ALL_PARKING_GARAGES_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.ParkingGarageConstants.GET_PARKING_GARAGE_BY_ID_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.ParkingGarageConstants.UPDATE_PARKING_GARAGE_TEXT;

@Slf4j
@Tag(name = "Parking Garage", description = "Operations for managing parking garages.")
@RequiredArgsConstructor
@RequestMapping("/v1/parking-garages")
@RestController
public class ParkingGarageController {

    private final ParkingGarageService parkingGarageService;
    private final Converter<ParkingGarageDTO, ParkingGarageResponse> parkingGarageDtoToResponseConverter;

    @Operation(summary = "Returns all Parking Garages.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = ParkingGarageResponse.class
                                    )
                            )
                    )}
            )
    })
    @GetMapping
    public ResponseEntity<List<ParkingGarageResponse>> getAllParkingGarages() {

        log.info(GET_ALL_PARKING_GARAGES_TEXT);
        return ResponseEntity.ok(
                parkingGarageService
                        .getAllParkingGarages()
                        .stream()
                        .map(parkingGarageDtoToResponseConverter::convert)
                        .toList()
        );
    }

    @Operation(summary = "Returns a Parking Garage.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ParkingGarageResponse.class)
                    )}
            ),
            @ApiResponse(responseCode = "404", description = "Parking Garage not found.",
                    content = {@Content(schema = @Schema(hidden = true))}),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ParkingGarageResponse> getParkingGarageById(
            @PathVariable final Long id) {

        log.info(GET_PARKING_GARAGE_BY_ID_TEXT, id);
        Optional<ParkingGarageDTO> parkingGarage = parkingGarageService.getParkingGarage(id);
        return parkingGarage.map(parkingGarageDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Adds a Parking Garage.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ParkingGarageResponse.class)
                    )}
            ),
            @ApiResponse(responseCode = "400", description = "Invalid Parking Garage supplied.",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(responseCode = "422", description = "The request body is empty or not well-formed.",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(responseCode = "409", description = "A Parking garage already exist with the given address.",
                    content = {@Content(schema = @Schema(hidden = true))})
    })
    @PostMapping
    public ResponseEntity<ParkingGarageResponse> addParkingGarage(
            @Valid @RequestBody final ParkingGarageDTO parkingGarage,
            BindingResult bindingResult) {

        log.info(CREATE_PARKING_GARAGE_TEXT, parkingGarage);
        checkForRequestErrors(bindingResult);
        return Optional.of(parkingGarageService.createParkingGarage(parkingGarage))
                .map(parkingGarageDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Adds a Parking Garage.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ParkingGarageResponse.class)
                    )}
            ),
            @ApiResponse(responseCode = "400", description = "Invalid Parking Garage supplied.",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(responseCode = "422", description = "The request body is empty or not well-formed.",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(responseCode = "409", description = "A Parking Garage already exist with the given address.",
                    content = {@Content(schema = @Schema(hidden = true))})
    })
    @PutMapping("/{id}")
    public ResponseEntity<ParkingGarageResponse> updateParkingGarage(
            @PathVariable final Long id,
            @Valid @RequestBody final ParkingGarageDTO parkingGarage,
            BindingResult bindingResult) {

        log.info(UPDATE_PARKING_GARAGE_TEXT, id, parkingGarage);
        checkForRequestErrors(bindingResult);
        return Optional.of(parkingGarageService.updateParkingGarage(id, parkingGarage))
                .map(parkingGarageDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Deletes a Parking Garage.",
            description = "This operation deletes a Parking Garage, and all associated parking records.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success",
                    content = {@Content(schema = @Schema(hidden = true))}),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingGarage(
            @PathVariable final Long id) {

        log.info(DELETE_PARKING_GARAGE_TEXT, id);
        parkingGarageService.deleteParkingGarage(id);
        return ResponseEntity.noContent().build();
    }

    private void checkForRequestErrors(final BindingResult bindingResult) {
        List<String> errors = RequestValidationHandlerUtil.getRequestErrors(bindingResult);
        if (!errors.isEmpty()) {
            throw new InvalidParkingGarageRequestException("Invalid parking garage request", errors);
        }
    }
}