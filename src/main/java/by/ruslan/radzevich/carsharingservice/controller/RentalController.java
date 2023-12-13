package by.ruslan.radzevich.carsharingservice.controller;

import by.ruslan.radzevich.carsharingservice.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @PostMapping("/rent")
    public void rentCar(@RequestParam Long carId, @RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime, @RequestParam double costPerMinute) {
        rentalService.rentCar(carId, startTime, endTime, costPerMinute);
    }

    @GetMapping("/calculateTotalCost")
    public double calculateTotalCost(@RequestParam Long rentalId) {
        return rentalService.calculateTotalCost(rentalId);
    }
}
