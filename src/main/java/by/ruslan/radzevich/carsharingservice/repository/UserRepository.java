package by.ruslan.radzevich.carsharingservice.repository;


import by.ruslan.radzevich.carsharingservice.model.User;
import by.ruslan.radzevich.carsharingservice.repository.view.CarsView;
import by.ruslan.radzevich.carsharingservice.repository.view.UserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    List<UserView> findAllBy();
    @Modifying
    @Query("update User u set u.email = :email where u.id = :id")
    void updateEmailByID(@Param(value = "id") long id, @Param(value = "email") String email);

    Optional<User> findByUsername(String username);
}
