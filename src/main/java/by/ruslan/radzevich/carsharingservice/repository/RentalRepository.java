package by.ruslan.radzevich.carsharingservice.repository;

import by.ruslan.radzevich.carsharingservice.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
