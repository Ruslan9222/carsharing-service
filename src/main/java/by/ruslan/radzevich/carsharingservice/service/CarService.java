package by.ruslan.radzevich.carsharingservice.service;

import by.ruslan.radzevich.carsharingservice.model.Car;
import by.ruslan.radzevich.carsharingservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> findCarsNearby(double latitude, double longitude, double radius) {
        double minLat = latitude - radius;
        double maxLat = latitude + radius;
        double minLong = longitude - radius;
        double maxLong = longitude + radius;

        return carRepository.findByLatitudeBetweenAndLongitudeBetween(minLat, maxLat, minLong, maxLong);
    }
}
