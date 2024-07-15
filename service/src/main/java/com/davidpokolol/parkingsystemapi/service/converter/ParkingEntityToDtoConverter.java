package com.davidpokolol.parkingsystemapi.service.converter;

import com.davidpokolol.parkingsystemapi.model.Parking;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingDTO;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ParkingEntityToDtoConverter implements Converter<Parking, ParkingDTO> {


    @Override
    public ParkingDTO convert(@Nonnull final Parking source) {

        log.info("Convert Parking:{} to ParkingDTO", source);
        return new ParkingDTO(
                source.getId(),
                source.getVehicles().get(0).getLicensePlate(),
                source.getParkingGarage().getId(),
                source.getStartTime(),
                source.getEndTime()
        );
    }
}
