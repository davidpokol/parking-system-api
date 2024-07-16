package com.davidpokolol.parkingsystemapi.service.converter;

import com.davidpokolol.parkingsystemapi.model.Vehicle;
import com.davidpokolol.parkingsystemapi.service.model.dto.VehicleDTO;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VehicleEntityToDtoConverter implements Converter<Vehicle, VehicleDTO> {

    @Override
    public VehicleDTO convert(@NonNull final Vehicle source) {

        log.info("Convert Vehicle:{} to VehicleDTO", source);
        return new VehicleDTO(
                source.getId(),
                source.getLicensePlate(),
                source.getVehicleCategory().toString()
        );
    }
}
