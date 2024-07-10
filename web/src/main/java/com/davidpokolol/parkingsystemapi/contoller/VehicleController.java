package com.davidpokolol.parkingsystemapi.contoller;

import com.davidpokolol.parkingsystemapi.model.response.ParkingGarageResponse;
import com.davidpokolol.parkingsystemapi.model.response.VehicleResponse;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingGarageDTO;
import com.davidpokolol.parkingsystemapi.service.model.dto.VehicleDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
@Slf4j
public class VehicleController {

    private final Converter<VehicleDTO, VehicleResponse> vehicleDtoToResponseConverter;

    /*@GetMapping
    public ResponseEntity<List<VehicleDTO>> findAll() {
        List<VehicleDTO> vehicle = vehicleService.findById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable Long id) {
        Optional<VehicleDTO> car = vehicleService.findById(id);
    }*/

    @PostMapping
    public ResponseEntity<VehicleResponse> addVehicle(@RequestBody final VehicleDTO vehicleDTO) {

        final VehicleResponse vehicle = vehicleDtoToResponseConverter.convert(
                vehicleDTO
        );
        return ResponseEntity.ok(vehicle);
    }


}
