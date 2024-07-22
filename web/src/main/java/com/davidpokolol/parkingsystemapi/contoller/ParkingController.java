package com.davidpokolol.parkingsystemapi.contoller;

import com.davidpokolol.parkingsystemapi.model.dto.ParkingDTO;
import com.davidpokolol.parkingsystemapi.model.exception.InvalidParkingRequestException;
import com.davidpokolol.parkingsystemapi.model.response.ParkingGarageResponse;
import com.davidpokolol.parkingsystemapi.model.response.ParkingResponse;
import com.davidpokolol.parkingsystemapi.service.ParkingService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.davidpokolol.parkingsystemapi.constant.ParkingConstants.CREATE_PARKING_RECORD_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.ParkingConstants.GET_ALL_PARKING_RECORDS_TEXT;
import static com.davidpokolol.parkingsystemapi.constant.ParkingConstants.GET_PARKING_RECORD_BY_ID_TEXT;

@Slf4j
@Tag(name = "Parking", description = "Operations for managing parkings.")
@RequiredArgsConstructor
@RequestMapping("/v1/parking-records")
@RestController
public class ParkingController {

    private final ParkingService parkingService;
    private final Converter<ParkingDTO, ParkingResponse> parkingDtoToResponseConverter;

    @Operation(summary = "Returns all parkings.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = ParkingResponse.class
                                    )
                            )
                    )}
            )
    })
    @GetMapping
    public ResponseEntity<List<ParkingResponse>> getAllParkingRecords() {
        log.info(GET_ALL_PARKING_RECORDS_TEXT);

        return ResponseEntity.ok(
                parkingService
                        .getAllParkingRecords()
                        .stream()
                        .map(parkingDtoToResponseConverter::convert)
                        .toList()
        );
    }

    @Operation(summary = "Returns a parking record.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ParkingGarageResponse.class)
                    )}
            ),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(responseCode = "404", description = "Parking not found.",
                    content = {@Content(schema = @Schema(hidden = true))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<ParkingResponse> getParkingRecord(@PathVariable final Long id) {

        log.info(GET_PARKING_RECORD_BY_ID_TEXT, id);
        Optional<ParkingDTO> parkingRecord = parkingService.getParkingRecord(id);
        return parkingRecord.map(parkingDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Adds a Parking record.",
            description = "The startTime and endTime properties cannot represent the same date.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ParkingGarageResponse.class)
                    )}
            ),
            @ApiResponse(responseCode = "400", description = "Invalid Parking record supplied.",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(responseCode = "404", description = "Parking not found.",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(responseCode = "422", description = "The request body is empty or not well-formed.",
                    content = {@Content(schema = @Schema(hidden = true))})
    })
    @PostMapping
    public ResponseEntity<ParkingResponse> addParkingGarage(@Valid @RequestBody final ParkingDTO parking,
                                                            BindingResult bindingResult) {

        log.info(CREATE_PARKING_RECORD_TEXT, parking);
        checkForRequestErrors(bindingResult);
        return Optional.of(parkingService.createParkingRecord(parking))
                .map(parkingDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private void checkForRequestErrors(final BindingResult bindingResult) {
        List<String> errors = RequestValidationHandlerUtil.getRequestErrors(bindingResult);
        if (!errors.isEmpty()) {
            throw new InvalidParkingRequestException("Invalid parking request", errors);
        }
    }
}