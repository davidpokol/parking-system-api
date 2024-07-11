package com.davidpokolol.parkingsystemapi.service.converter;

import com.davidpokolol.parkingsystemapi.model.ParkingGarage;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingGarageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ParkingGarageEntityToDtoConverter implements Converter<ParkingGarage, ParkingGarageDTO> {

    @Override
    public ParkingGarageDTO convert(ParkingGarage source) {

        log.info("Convert ParkingGarage:{} to ParkingGarageDTO", source);
        return new ParkingGarageDTO(
                source.getId(),
                source.getAddress(),
                source.getParkingSpaces()
        );
    }
}
