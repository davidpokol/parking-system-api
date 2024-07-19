package com.davidpokolol.parkingsystemapi.service.converter.EntityToDto;

import com.davidpokolol.parkingsystemapi.model.ParkingGarage;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingGarageDTO;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ParkingGarageEntityToDtoConverter implements Converter<ParkingGarage, ParkingGarageDTO> {

    @Override
    public ParkingGarageDTO convert(@Nonnull final ParkingGarage source) {

        log.info("Convert ParkingGarage:{} to ParkingGarageDTO.", source);
        return new ParkingGarageDTO(
                source.getId(),
                source.getAddress(),
                source.getParkingSpaces()
        );
    }
}
