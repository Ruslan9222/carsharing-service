package by.ruslan.radzevich.carsharingservice.service;

import by.ruslan.radzevich.carsharingservice.model.Rental;
import by.ruslan.radzevich.carsharingservice.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    public void rentCar(Long carId, LocalDateTime startTime, LocalDateTime endTime, double costPerMinute) {
        Rental rental = new Rental();
        rental.setCarId(carId);
        rental.setStartTime(startTime);
        rental.setEndTime(endTime);
        rental.setCostPerMinute(costPerMinute);

        rentalRepository.save(rental);
    }
    public double calculateTotalCost(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElse(null);

        if (rental != null) {
            long minutesRented = Duration.between(rental.getStartTime(), rental.getEndTime()).toMinutes();
            return rental.getCostPerMinute() * minutesRented;
        } else {
            return 0.0;
        }
    }
}
