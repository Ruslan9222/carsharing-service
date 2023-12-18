package by.ruslan.radzevich.carsharingservice.repository;

import by.ruslan.radzevich.carsharingservice.model.CarPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarPriceRepository extends JpaRepository<CarPrice,Long> {
}
