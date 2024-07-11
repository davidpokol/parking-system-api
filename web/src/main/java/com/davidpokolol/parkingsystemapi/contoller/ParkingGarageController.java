package com.davidpokolol.parkingsystemapi.contoller;

import com.davidpokolol.parkingsystemapi.model.response.ParkingGarageResponse;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingGarageDTO;
import com.davidpokolol.parkingsystemapi.service.service.ParkingGarageService;
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

import static com.davidpokolol.parkingsystemapi.service.constant.ParkingGarageConstants.CREATE_PARKING_GARAGE_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.ParkingGarageConstants.DELETE_PARKING_GARAGE_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.ParkingGarageConstants.GET_ALL_PARKING_GARAGES_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.ParkingGarageConstants.GET_PARKING_GARAGE_BY_ID_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.ParkingGarageConstants.UPDATE_PARKING_GARAGE_TEXT;

@RestController
@RequestMapping("/parking-garages")
@RequiredArgsConstructor
@Slf4j
public class ParkingGarageController {

    private final ParkingGarageService parkingGarageService;
    private final Converter<ParkingGarageDTO, ParkingGarageResponse> parkingGarageDtoToResponseConverter;


    @GetMapping
    public ResponseEntity<List<ParkingGarageResponse>> getAllParkingGarages() {

        log.info(GET_ALL_PARKING_GARAGES_TEXT);
        List<ParkingGarageResponse> parkingGarages =
                parkingGarageService
                        .getAllParkingGarages()
                        .stream()
                        .map(parkingGarageDtoToResponseConverter::convert)
                        .toList();

        return ResponseEntity.ok(parkingGarages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingGarageResponse> getParkingGarageById(
            @PathVariable final Long id) {

        log.info(GET_PARKING_GARAGE_BY_ID_TEXT, id);
        Optional<ParkingGarageDTO> parkingGarage = parkingGarageService.getParkingGarage(id);
        return parkingGarage.map(parkingGarageDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ParkingGarageResponse> addParkingGarage(
            @RequestBody final ParkingGarageDTO parkingGarage) {

        log.info(CREATE_PARKING_GARAGE_TEXT, parkingGarage);
        return Optional.of(parkingGarageService.createParkingGarage(parkingGarage))
                .map(parkingGarageDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingGarageResponse> updateParkingGarage(
            @PathVariable final Long id, @RequestBody final ParkingGarageDTO parkingGarage) {

        log.info(UPDATE_PARKING_GARAGE_TEXT, id, parkingGarage);
        return Optional.of(parkingGarageService.updateParkingGarage(id, parkingGarage))
                .map(parkingGarageDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingGarage(
            @PathVariable final Long id) {

        log.info(DELETE_PARKING_GARAGE_TEXT, id);
        parkingGarageService.deleteParkingGarage(id);
        return ResponseEntity.noContent().build();
    }
}