package com.davidpokolol.parkingsystemapi.converter.EntityToDto;

import com.davidpokolol.parkingsystemapi.model.Vehicle;
import com.davidpokolol.parkingsystemapi.model.dto.VehicleDTO;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VehicleEntityToDtoConverter implements Converter<Vehicle, VehicleDTO> {

    @Override
    public VehicleDTO convert(@NonNull final Vehicle source) {

        log.info("Convert Vehicle:{} to VehicleDTO.", source);
        return new VehicleDTO(
                source.getId(),
                source.getLicensePlate(),
                source.getVehicleCategory().toString()
        );
    }
}
