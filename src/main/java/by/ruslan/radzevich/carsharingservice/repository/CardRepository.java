package by.ruslan.radzevich.carsharingservice.repository;

import by.ruslan.radzevich.carsharingservice.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    ///
}
