package com.davidpokolol.parkingsystemapi.contoller;

import com.davidpokolol.parkingsystemapi.model.response.ParkingResponse;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkings")
@RequiredArgsConstructor
@Slf4j
public class ParkingController {

    private final Converter<ParkingDTO, ParkingResponse> convertParkingDtoToResponse;

    @PostMapping
    public ResponseEntity<ParkingResponse> addParkingGarage(@RequestBody final ParkingDTO parkingDTO) {

        final ParkingResponse parkingResponse = convertParkingDtoToResponse.convert(
                parkingDTO
        );
        return ResponseEntity.ok(parkingResponse);
    }
}
