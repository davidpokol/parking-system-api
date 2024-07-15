package com.davidpokolol.parkingsystemapi.contoller;

import com.davidpokolol.parkingsystemapi.model.response.ParkingResponse;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingDTO;
import com.davidpokolol.parkingsystemapi.service.service.ParkingService;
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

import static com.davidpokolol.parkingsystemapi.service.constant.ParkingConstants.CREATE_PARKING_RECORD_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.ParkingConstants.GET_ALL_PARKING_RECORDS_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.ParkingConstants.GET_PARKING_RECORD_BY_ID_TEXT;

@RestController
@RequestMapping("/parking-records")
@RequiredArgsConstructor
@Slf4j
public class ParkingController {

    private final ParkingService parkingService;
    public final Converter<ParkingDTO, ParkingResponse> parkingDtoToResponseConverter;

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

    @GetMapping("/{id}")
    public ResponseEntity<ParkingResponse> getParkingRecord(@PathVariable final Long id) {

        log.info(GET_PARKING_RECORD_BY_ID_TEXT, id);
        Optional<ParkingDTO> parkingRecord = parkingService.getParkingRecord(id);
        return parkingRecord.map(parkingDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ParkingResponse> addParkingGarage(@RequestBody final ParkingDTO parking) {

        log.info(CREATE_PARKING_RECORD_TEXT, parking);
        return Optional.of(parkingService.createParkingRecord(parking))
                .map(parkingDtoToResponseConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}