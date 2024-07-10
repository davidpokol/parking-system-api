package com.davidpokolol.parkingsystemapi.converter;

import com.davidpokolol.parkingsystemapi.model.response.ParkingGarageResponse;
import com.davidpokolol.parkingsystemapi.model.response.ParkingResponse;
import com.davidpokolol.parkingsystemapi.model.response.VehicleResponse;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingDTO;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingGarageDTO;
import com.davidpokolol.parkingsystemapi.service.model.dto.VehicleDTO;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ParkingDtoToResponseConverter implements Converter<ParkingDTO, ParkingResponse> {

    private final Converter<ParkingGarageDTO, ParkingGarageResponse> parkingGarageDtoToResponseConverter;
    private final Converter<VehicleDTO, VehicleResponse> vehicleDtoToResponseConverter;

    @Override
    public ParkingResponse convert(@Nonnull final ParkingDTO source) {

        log.info("Convert ParkingDTO:{} to ParkingResponse.", source);
        return new ParkingResponse(
                source.id(),
                vehicleDtoToResponseConverter.convert(source.vehicle()),
                parkingGarageDtoToResponseConverter.convert(source.parkingGarage()),
                source.startTime(),
                source.endTime()
        );
    }
}
