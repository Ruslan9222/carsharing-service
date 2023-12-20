package by.ruslan.radzevich.carsharingservice.controller;

import by.ruslan.radzevich.carsharingservice.dto.NewCarDto;
import by.ruslan.radzevich.carsharingservice.mapper.CreateAutoMapper;
import by.ruslan.radzevich.carsharingservice.model.Car;
import by.ruslan.radzevich.carsharingservice.model.CarPrice;
import by.ruslan.radzevich.carsharingservice.repository.CarPriceRepository;
import by.ruslan.radzevich.carsharingservice.repository.CarRepository;
import by.ruslan.radzevich.carsharingservice.repository.view.CarsView;
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
    private final CreateAutoMapper createAutoMapper;
    private final CarPriceRepository carPriceRepository;


    @PostMapping()
    public ResponseEntity<Car> create(@RequestBody NewCarDto newCarDto) {
        Car addCar = createAutoMapper.newAutoDtoToAuto(newCarDto);
        carRepository.save(addCar);
        return ResponseEntity.ok(addCar);
    }

    @PostMapping("/price")
    public ResponseEntity<CarPrice> create(@RequestBody CarPrice carPrice) {
        CarPrice price = new CarPrice();
        price.setPrice(carPrice.getPrice());
        carPriceRepository.save(price);
        return ResponseEntity.ok(price);
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
    @GetMapping("/all")
    public ResponseEntity<List<CarsView>> findAll() {
        return ResponseEntity.ok(carRepository.findAllBy());
    }

}
