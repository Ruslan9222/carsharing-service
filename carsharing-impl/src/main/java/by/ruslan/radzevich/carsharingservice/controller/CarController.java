package by.ruslan.radzevich.carsharingservice.controller;

import by.ruslan.radzevich.carsharingservice.dto.request.CreateCarRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.response.CreateCarResponseDto;
import by.ruslan.radzevich.carsharingservice.service.CarService;
import by.ruslan.radzevich.model.entity.Car;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cars")
@Tag(name = "CarController", description = "Контроллер для управления автомобилями")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;


    @Operation(
        description = "API предназначено для добавления автомобиля в базу"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "404",
            description = "Not Found",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "409", description = "Conflict",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "500",
            description = "Internal Server Error",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "503", description = "Service Unavailable",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
    })

    @PostMapping()
    public CreateCarResponseDto create(
        @Valid @RequestBody CreateCarRequestDto createCarRequestDto) {
        return carService.createCar(createCarRequestDto);
    }
//
//    @PostMapping("/price")
//    public ResponseEntity<CarPrice> create(@RequestBody CarPrice carPrice) {
//        CarPrice price = new CarPrice();
//        price.setPrice(carPrice.getPrice());
//        carPriceRepository.save(price);
//        return ResponseEntity.ok(price);
//    }

//    @SneakyThrows
//    @PutMapping("/{id}/carPhoto")
//    public ResponseEntity<Car> carPhoto(@RequestBody Long id,
//        @RequestPart("photo") MultipartFile photo) {
//        Car car = carRepository.findById(id).orElseThrow();
//        car.setPhoto(photo.getBytes());
//        Car save = carRepository.save(car);
//        return ResponseEntity.ok(save);
//    }

    @GetMapping("/nearby")
    public List<Car> getCarsNearby(@RequestParam double latitude,
        @RequestParam double longitude,
        @RequestParam double radius) {
        return carService.findCarsNearby(latitude, longitude, radius);
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<CarsView>> findAll() {
//        return ResponseEntity.ok(carRepository.findAllBy());
//    }

}
