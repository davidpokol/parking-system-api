package hu.foursys.ParkingSystem.controller;

import hu.foursys.ParkingSystem.dto.ParkingDataDTO;
import hu.foursys.ParkingSystem.exception.InvalidRequestException;
import hu.foursys.ParkingSystem.response.ParkingDataResponse;
import hu.foursys.ParkingSystem.service.ParkingService;
import hu.foursys.ParkingSystem.util.RequestUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Slf4j
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;
    private final RequestUtil requestUtil;

    @GetMapping
    public ResponseEntity<List<ParkingDataResponse>> findAll() {
        List<ParkingDataResponse> parkingList = parkingService.findAll();
        return ResponseEntity.ok().body(parkingList);
    }

    @PostMapping("/add")
    public ResponseEntity<ParkingDataResponse> create(
            @Valid @RequestBody ParkingDataDTO parkingDataDTO,
            BindingResult bindingResult)
            throws InvalidRequestException {
        requestUtil.checkForRequestErrors(bindingResult);

        ParkingDataResponse savedParking = parkingService.create(parkingDataDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedParking);
    }

}
