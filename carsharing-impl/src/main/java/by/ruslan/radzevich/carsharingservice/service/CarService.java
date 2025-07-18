//package by.ruslan.radzevich.carsharingservice.service;
//
//import by.ruslan.radzevich.carsharingservice.model.Car;
//import by.ruslan.radzevich.carsharingservice.repository.CarRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class CarService {
//
//    private final CarRepository carRepository;
//
//    public List<Car> findCarsNearby(double latitude, double longitude, double radius) {
//        double minLat = latitude - radius;
//        double maxLat = latitude + radius;
//        double minLong = longitude - radius;
//        double maxLong = longitude + radius;
//
//        return carRepository.findByLatitudeBetweenAndLongitudeBetween(minLat, maxLat, minLong, maxLong);
//    }
//}
