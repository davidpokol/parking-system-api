package com.davidpokolol.parkingsystemapi.service.converter;

import com.davidpokolol.parkingsystemapi.model.Vehicle;
import com.davidpokolol.parkingsystemapi.service.model.dto.VehicleDTO;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VehicleDtoToEntityConverter implements Converter<VehicleDTO, Vehicle> {

    @Override
    public Vehicle convert(@NonNull final VehicleDTO source) {

        log.info("Convert VehicleDTO:{} to Vehicle", source);
        return new Vehicle(
                null,
                source.licensePlate(),
                source.vehicleCategory()
        );
    }
}
