package by.ruslan.radzevich.carsharingservice.controller;

import by.ruslan.radzevich.carsharingservice.dto.NewCarDto;
import by.ruslan.radzevich.carsharingservice.model.Car;
import by.ruslan.radzevich.carsharingservice.repository.CarRepository;
import by.ruslan.radzevich.carsharingservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private CarRepository carRepository;


    @PostMapping("/car")
//    ResponseEntity<Car> create(@RequestBody NewCarDto newCarDto) {
////        Car car = carRepository.save(newCarDto);
////        return ResponseEntity.ok(car);
//    }

    @GetMapping("/nearby")
    public List<Car> getCarsNearby(@RequestParam double latitude,
                                   @RequestParam double longitude,
                                   @RequestParam double radius) {
        return carService.findCarsNearby(latitude, longitude, radius);
    }
}
