package by.ruslan.radzevich.carsharingservice.repository;

import by.ruslan.radzevich.carsharingservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByLatitudeBetweenAndLongitudeBetween(double minLat,
                                                       double maxLat,
                                                       double minLong,
                                                       double maxLong);
}
