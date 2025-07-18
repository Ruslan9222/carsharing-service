//package by.ruslan.radzevich.carsharingservice.controller;
//
//
//
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/cars")
//public class CarController {
//
//    private final CarService carService;
//    private final CarRepository carRepository;
//    private final CreateAutoMapper createAutoMapper;
//    private final CarPriceRepository carPriceRepository;
//
//
//    @PostMapping()
//    public ResponseEntity<Car> create(@RequestBody NewCarDto newCarDto) {
//        Car addCar = createAutoMapper.newAutoDtoToAuto(newCarDto);
//        carRepository.save(addCar);
//        return ResponseEntity.ok(addCar);
//    }
//
//    @PostMapping("/price")
//    public ResponseEntity<CarPrice> create(@RequestBody CarPrice carPrice) {
//        CarPrice price = new CarPrice();
//        price.setPrice(carPrice.getPrice());
//        carPriceRepository.save(price);
//        return ResponseEntity.ok(price);
//    }
//
//
//    @SneakyThrows
//    @PutMapping("/{id}/carPhoto")
//    public ResponseEntity<Car> carPhoto(@RequestBody Long id,
//                                        @RequestPart("photo") MultipartFile photo) {
//        Car car = carRepository.findById(id).orElseThrow();
//        car.setPhoto(photo.getBytes());
//        Car save = carRepository.save(car);
//        return ResponseEntity.ok(save);
//    }
//
//    @GetMapping("/nearby")
//    public List<Car> getCarsNearby(@RequestParam double latitude,
//                                   @RequestParam double longitude,
//                                   @RequestParam double radius) {
//        return carService.findCarsNearby(latitude, longitude, radius);
//    }
//    @GetMapping("/all")
//    public ResponseEntity<List<CarsView>> findAll() {
//        return ResponseEntity.ok(carRepository.findAllBy());
//    }
//
//}
