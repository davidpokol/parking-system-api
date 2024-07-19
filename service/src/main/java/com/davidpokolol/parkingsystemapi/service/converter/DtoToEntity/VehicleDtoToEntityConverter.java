package com.davidpokolol.parkingsystemapi.service.converter.DtoToEntity;

import com.davidpokolol.parkingsystemapi.model.Vehicle;
import com.davidpokolol.parkingsystemapi.model.enums.VehicleCategory;
import com.davidpokolol.parkingsystemapi.service.model.dto.VehicleDTO;
import com.davidpokolol.parkingsystemapi.service.util.ConverterUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VehicleDtoToEntityConverter implements Converter<VehicleDTO, Vehicle> {


    @Override
    public Vehicle convert(@NonNull final VehicleDTO source) {

        log.info("Convert VehicleDTO:{} to Vehicle.", source);
        return new Vehicle(
                null,
                ConverterUtil.formatHungarianLicensePlate(source.licensePlate()),
                ConverterUtil.convertStringToEnum(
                        VehicleCategory.class,
                        source.vehicleCategory().toUpperCase()
                )
        );
    }
}
