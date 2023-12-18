package by.ruslan.radzevich.carsharingservice.controller;

import by.ruslan.radzevich.carsharingservice.model.Car;
import by.ruslan.radzevich.carsharingservice.repository.CarRepository;
import by.ruslan.radzevich.carsharingservice.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CarRepository carRepository;


    @PostMapping()
    public ResponseEntity<Car> create(@RequestBody Car car) {
        Car addCar = new Car();
        addCar.setModel(car.getModel());
        addCar.setWinCode(car.getWinCode());
        carRepository.save(addCar);
        return ResponseEntity.ok(car);
    }

    @SneakyThrows
    @PutMapping("/{id}/carPhoto")
    public ResponseEntity<Car> carPhoto(@RequestBody Long id,
                                        @RequestPart("photo") MultipartFile photo) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setPhoto(photo.getBytes());
        Car save = carRepository.save(car);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/nearby")
    public List<Car> getCarsNearby(@RequestParam double latitude,
                                   @RequestParam double longitude,
                                   @RequestParam double radius) {
        return carService.findCarsNearby(latitude, longitude, radius);
    }

}
