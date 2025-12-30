package by.ruslan.radzevich.carsharingservice.service.impl;


import by.ruslan.radzevich.carsharingservice.dto.request.CreateCarRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.response.CreateCarResponseDto;
import by.ruslan.radzevich.carsharingservice.exception.CarException;
import by.ruslan.radzevich.carsharingservice.mapper.CarMapper;
import by.ruslan.radzevich.carsharingservice.service.CarService;
import by.ruslan.radzevich.model.entity.Car;
import by.ruslan.radzevich.repository.CarRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    @Transactional
    public CreateCarResponseDto createCar(CreateCarRequestDto createCarRequestDto) {
        if (carRepository.existsByWinCode(createCarRequestDto.winCode())) {
            throw new CarException("Автомобиль с таким VIN уже существует");
        }
        Car car = carMapper.mapToEntity(createCarRequestDto);
        Car savedCar = carRepository.save(car);
        return carMapper.mapToResponseDto(savedCar);
    }

    @Override
    @Transactional
    public List<Car> findCarsNearby(double latitude, double longitude, double radius) {
        double minLat = latitude - radius;
        double maxLat = latitude + radius;
        double minLong = longitude - radius;
        double maxLong = longitude + radius;

        return carRepository.findByLatitudeBetweenAndLongitudeBetween(minLat, maxLat, minLong,
            maxLong);
    }
}
