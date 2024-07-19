package com.davidpokolol.parkingsystemapi.converter;

import com.davidpokolol.parkingsystemapi.model.exception.EntityNotFoundException;
import com.davidpokolol.parkingsystemapi.model.response.ParkingGarageResponse;
import com.davidpokolol.parkingsystemapi.model.response.ParkingResponse;
import com.davidpokolol.parkingsystemapi.model.response.VehicleResponse;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingDTO;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingGarageDTO;
import com.davidpokolol.parkingsystemapi.service.model.dto.VehicleDTO;
import com.davidpokolol.parkingsystemapi.service.service.ParkingGarageService;
import com.davidpokolol.parkingsystemapi.service.service.VehicleService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.davidpokolol.parkingsystemapi.service.constant.ParkingGarageConstants.PARKING_GARAGE_NOT_FOUND_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.VEHICLE_NOT_FOUND_WITH_LICENSE_PLATE_TEXT;

@Slf4j
@RequiredArgsConstructor
@Component
public class ParkingDtoToResponseConverter implements Converter<ParkingDTO, ParkingResponse> {


    private final Converter<VehicleDTO, VehicleResponse> vehicleDtoToResponseConverter;
    private final Converter<ParkingGarageDTO, ParkingGarageResponse> parkingGarageDtoToResponseConverter;
    private final VehicleService vehicleService;
    private final ParkingGarageService parkingGarageService;

    @Override
    public ParkingResponse convert(@Nonnull final ParkingDTO source) {

        log.info("Convert ParkingDTO:{} to ParkingResponse.", source);
        return new ParkingResponse(
                source.id(),
                convertVehicle(source.vehicleLicensePlate()),
                convertParkingGarage(source.parkingGarageId()),
                source.startTime(),
                source.endTime()
        );
    }

    private VehicleResponse convertVehicle(final String licensePlate) {
        return vehicleService.getVehicle(licensePlate)
                .map(vehicleDtoToResponseConverter::convert)
                .orElseThrow(() -> new EntityNotFoundException(
                        VEHICLE_NOT_FOUND_WITH_LICENSE_PLATE_TEXT + licensePlate
                ));
    }

    private ParkingGarageResponse convertParkingGarage(final Long id) {
        return parkingGarageService.getParkingGarage(id)
                .map(parkingGarageDtoToResponseConverter::convert)
                .orElseThrow(() -> new EntityNotFoundException(
                        PARKING_GARAGE_NOT_FOUND_TEXT + id
                ));
    }
}
