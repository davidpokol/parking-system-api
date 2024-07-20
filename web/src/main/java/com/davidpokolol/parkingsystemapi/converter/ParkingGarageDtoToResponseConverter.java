package com.davidpokolol.parkingsystemapi.converter;

import com.davidpokolol.parkingsystemapi.model.response.ParkingGarageResponse;
import com.davidpokolol.parkingsystemapi.model.dto.ParkingGarageDTO;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ParkingGarageDtoToResponseConverter implements Converter<ParkingGarageDTO, ParkingGarageResponse> {

    @Override
    public ParkingGarageResponse convert(@Nonnull final ParkingGarageDTO source) {

        log.info("Convert ParkingDTO:{} to ParkingResponse.", source);
        return new ParkingGarageResponse(
                source.id(),
                source.address(),
                source.parkingSpaces()
        );
    }
}
