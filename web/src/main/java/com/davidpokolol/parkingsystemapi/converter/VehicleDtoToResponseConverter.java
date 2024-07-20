package com.davidpokolol.parkingsystemapi.converter;

import com.davidpokolol.parkingsystemapi.model.response.VehicleResponse;
import com.davidpokolol.parkingsystemapi.model.dto.VehicleDTO;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VehicleDtoToResponseConverter implements Converter<VehicleDTO, VehicleResponse> {

    @Override
    public VehicleResponse convert(@Nonnull final VehicleDTO source) {

        log.info("Convert VehicleDTO:{} to VehicleResponse.", source);
        return new VehicleResponse(
                source.id(),
                source.licensePlate(),
                source.vehicleCategory()
        );
    }
}
