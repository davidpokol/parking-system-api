package com.davidpokolol.parkingsystemapi.service.converter.DtoToEntity;

import com.davidpokolol.parkingsystemapi.model.ParkingGarage;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingGarageDTO;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ParkingGarageDtoToEntityConverter implements Converter<ParkingGarageDTO, ParkingGarage> {

    @Override
    public ParkingGarage convert(@Nonnull final ParkingGarageDTO source) {

        log.info("Convert ParkingGarageDTO:{} to ParkingGarage.", source);
        return new ParkingGarage(
                null,
                source.address(),
                source.parkingSpaces()
        );
    }
}
