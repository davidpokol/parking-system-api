package hu.foursys.ParkingSystem.service.impl;

import hu.foursys.ParkingSystem.dto.CarDTO;
import hu.foursys.ParkingSystem.dto.ParkingDataDTO;
import hu.foursys.ParkingSystem.dto.ParkingGarageDTO;
import hu.foursys.ParkingSystem.entity.Car;
import hu.foursys.ParkingSystem.entity.ParkingData;
import hu.foursys.ParkingSystem.entity.ParkingGarage;
import hu.foursys.ParkingSystem.exception.CarNotFoundException;
import hu.foursys.ParkingSystem.exception.ParkingGarageNotFoundException;
import hu.foursys.ParkingSystem.repository.CarRepository;
import hu.foursys.ParkingSystem.repository.GarageRepository;
import hu.foursys.ParkingSystem.repository.ParkingRepository;
import hu.foursys.ParkingSystem.response.ParkingDataResponse;
import hu.foursys.ParkingSystem.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private final CarRepository carRepository;
    private final GarageRepository garageRepository;
    private final ParkingRepository parkingRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ParkingDataResponse> findAll() {

        List<ParkingDataDTO> allParkings = new ArrayList<>();
        return parkingRepository.findAll()
                .stream()
                .map(parking -> modelMapper.map(parking, ParkingDataResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ParkingDataResponse create(ParkingDataDTO parkingDataDTO) {

        String parkingGarageAddress = parkingDataDTO.getParkingGarageAddress();
        ParkingGarage parkingGarage = garageRepository.findByAddress(parkingGarageAddress)
                .orElseThrow(() -> new ParkingGarageNotFoundException(
                        String.format("Parking garage not found with address=%s", parkingGarageAddress)
                ));

        String carLicensePlate = parkingDataDTO.getCarLicensePlate();
        Car car = carRepository.findByLicensePlate(carLicensePlate)
                .orElseThrow(() -> new CarNotFoundException(
                        String.format("Car not found with license plate=%s", carLicensePlate)
                ));

        ParkingData savedParking = parkingRepository.save(
                mapParkingDataDTOToParkingData(parkingDataDTO, parkingGarage, car)
        );

        return mapParkingDataToParkingDataResponse(savedParking);
    }

    private ParkingData mapParkingDataDTOToParkingData(ParkingDataDTO parkingDataDTO,
                                                       ParkingGarage parkingGarage,
                                                       Car car) {
        parkingDataDTO.setId(null);
        parkingDataDTO.setParkingGarageAddress(null);
        parkingDataDTO.setCarLicensePlate(null);

        ParkingData parkingData = modelMapper.map(parkingDataDTO, ParkingData.class);
        parkingData.setParkingGarage(parkingGarage);
        parkingData.setCar(car);

        return parkingData;
    }

    private ParkingDataResponse mapParkingDataToParkingDataResponse(ParkingData parkingData) {
        ParkingGarage parkingGarage = parkingData.getParkingGarage();
        Car car = parkingData.getCar();

        parkingData.setParkingGarage(null);
        parkingData.setCar(null);

        ParkingDataResponse resultParking = modelMapper.map(parkingData, ParkingDataResponse.class);
        resultParking.setParkingGarage(modelMapper.map(parkingGarage, ParkingGarageDTO.class));
        resultParking.setCar(modelMapper.map(car,CarDTO.class));

        return resultParking;
    }
}
