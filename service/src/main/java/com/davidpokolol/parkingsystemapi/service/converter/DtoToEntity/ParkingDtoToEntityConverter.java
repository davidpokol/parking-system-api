package com.davidpokolol.parkingsystemapi.service.converter.DtoToEntity;

import com.davidpokolol.parkingsystemapi.model.Parking;
import com.davidpokolol.parkingsystemapi.model.ParkingGarage;
import com.davidpokolol.parkingsystemapi.model.Vehicle;
import com.davidpokolol.parkingsystemapi.model.exception.EntityNotFoundException;
import com.davidpokolol.parkingsystemapi.repository.ParkingGarageRepository;
import com.davidpokolol.parkingsystemapi.repository.VehicleRepository;
import com.davidpokolol.parkingsystemapi.service.model.dto.ParkingDTO;
import com.davidpokolol.parkingsystemapi.service.util.ConverterUtil;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.davidpokolol.parkingsystemapi.service.constant.ParkingGarageConstants.PARKING_GARAGE_NOT_FOUND_TEXT;
import static com.davidpokolol.parkingsystemapi.service.constant.VehicleConstants.VEHICLE_NOT_FOUND_WITH_LICENSE_PLATE_TEXT;


@Slf4j
@RequiredArgsConstructor
@Component
public class ParkingDtoToEntityConverter implements Converter<ParkingDTO, Parking> {

    private final ParkingGarageRepository parkingGarageRepository;
    private final VehicleRepository vehicleRepository;


    @Override
    public Parking convert(@Nonnull final ParkingDTO source) {

        log.info("Convert ParkingDTO:{} to Parking.", source);
        return new Parking(
                null,
                getVehicles(source.vehicleLicensePlate()),
                getParkingGarage(source.parkingGarageId()),
                source.startTime(),
                source.endTime()
        );
    }

    private ParkingGarage getParkingGarage(final Long id) {
        return parkingGarageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        PARKING_GARAGE_NOT_FOUND_TEXT + id
                ));
    }

    private List<Vehicle> getVehicles(final String licensePlate) {

        final String formattedLicensePlate = ConverterUtil.formatHungarianLicensePlate(licensePlate);
        return vehicleRepository.findByLicensePlateIgnoreCase(formattedLicensePlate)
                .map(List::of)
                .orElseThrow(() -> new EntityNotFoundException(
                        VEHICLE_NOT_FOUND_WITH_LICENSE_PLATE_TEXT + licensePlate
                ));
    }
}
