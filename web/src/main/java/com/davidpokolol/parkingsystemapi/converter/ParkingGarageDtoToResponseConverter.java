package com.davidpokolol.parkingsystemapi.converter;

import com.davidpokolol.parkingsystemapi.model.response.ParkingGarageResponse;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingGarageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ParkingGarageDtoToResponseConverter implements Converter<ParkingGarageDTO, ParkingGarageResponse> {


    @Override
    public ParkingGarageResponse convert(ParkingGarageDTO source) {

        log.info("Convert ParkingDTO:{} to ParkingResponse.", source);
        return new ParkingGarageResponse(
                source.id(),
                source.address(),
                source.parkingSpaces()
        );
    }
}
