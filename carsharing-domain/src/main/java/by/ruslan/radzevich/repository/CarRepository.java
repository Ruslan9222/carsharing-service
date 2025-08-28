package by.ruslan.radzevich.repository;


import by.ruslan.radzevich.view.CarsView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<by.ruslan.radzevich.model.entity.Car, Long> {

    List<CarsView> findAllBy();

    List<by.ruslan.radzevich.model.entity.Car> findByLatitudeBetweenAndLongitudeBetween(
        double minLat,
        double maxLat,
        double minLong,
        double maxLong);
}
