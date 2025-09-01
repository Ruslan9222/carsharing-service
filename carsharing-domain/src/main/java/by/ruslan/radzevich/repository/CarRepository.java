package by.ruslan.radzevich.repository;


import by.ruslan.radzevich.model.entity.Car;
import by.ruslan.radzevich.view.CarsView;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<CarsView> findAllBy();

    List<by.ruslan.radzevich.model.entity.Car> findByLatitudeBetweenAndLongitudeBetween(
        double minLat,
        double maxLat,
        double minLong,
        double maxLong);
}
