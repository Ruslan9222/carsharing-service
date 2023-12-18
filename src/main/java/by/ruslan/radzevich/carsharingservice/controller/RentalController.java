package by.ruslan.radzevich.carsharingservice.controller;

import by.ruslan.radzevich.carsharingservice.model.Rental;
import by.ruslan.radzevich.carsharingservice.repository.RentalRepository;
import by.ruslan.radzevich.carsharingservice.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    private final RentalRepository rentalRepository;

    @PostMapping
    public void rentCar(@RequestParam Long carId,
                        @RequestParam LocalDateTime startTime,
                        @RequestParam LocalDateTime endTime,
                        @RequestParam double costPerMinute) {
        rentalService.rentCar(carId, startTime, endTime, costPerMinute);
    }

    @SneakyThrows
    @PutMapping("/{id}/carPhoto")
    public ResponseEntity<Rental> carPhoto(@RequestBody Long id,
                                           @RequestPart("photo") MultipartFile photo,
                                           @RequestParam String comment) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        rental.setPhoto(photo.getBytes());
        rental.setComment(comment);
        Rental save = rentalRepository.save(rental);
        return ResponseEntity.ok(save);
    }



    @GetMapping("/calculateTotalCost")
    public double calculateTotalCost(@RequestParam Long rentalId) {
        return rentalService.calculateTotalCost(rentalId);
    }
}
