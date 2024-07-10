package com.davidpokolol.parkingsystemapi.contoller;

import com.davidpokolol.parkingsystemapi.model.response.ParkingGarageResponse;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingGarageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/garages")
@RequiredArgsConstructor
@Slf4j
public class ParkingGarageController {

    private final Converter<ParkingGarageDTO, ParkingGarageResponse> parkingGarageDtoToResponseConverter;

    @PostMapping
    public ResponseEntity<ParkingGarageResponse> addParkingGarage(@RequestBody final ParkingGarageDTO parkingGarageDTO) {

        final ParkingGarageResponse parkingGarage = parkingGarageDtoToResponseConverter.convert(
                parkingGarageDTO
        );
        return ResponseEntity.ok(parkingGarage);
    }
}
