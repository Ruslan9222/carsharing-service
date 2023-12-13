package by.ruslan.radzevich.carsharingservice.controller;

import by.ruslan.radzevich.carsharingservice.model.Car;
import by.ruslan.radzevich.carsharingservice.model.Rental;
import by.ruslan.radzevich.carsharingservice.repository.RentalRepository;
import by.ruslan.radzevich.carsharingservice.service.RentalService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;
    @Autowired
    private RentalRepository rentalRepository;

    @PostMapping("/rent")
    public void rentCar(@RequestParam Long carId, @RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime, @RequestParam double costPerMinute) {
        rentalService.rentCar(carId, startTime, endTime, costPerMinute);
    }
    @SneakyThrows
    @PutMapping("/{id}/carPhoto")
    public ResponseEntity<Rental> carPhoto(@RequestBody Long id,
                                           @RequestPart("photo") MultipartFile photo) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        rental.setPhoto(photo.getBytes());
        Rental save = rentalRepository.save(rental);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/calculateTotalCost")
    public ResponseEntity.BodyBuilder calculateTotalCost(@RequestParam Long rentalId) {
         rentalService.calculateTotalCost(rentalId);
        return ResponseEntity.ok();
    }
}
