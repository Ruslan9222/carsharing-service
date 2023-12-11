package by.ruslan.radzevich.carsharingservice.repository;


import by.ruslan.radzevich.carsharingservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query("update User u set u.email = :email where u.id = :id")
    void updateEmailByID(@Param(value = "id") long id, @Param(value = "email") String email);

    Optional<User> findByUsername(String username);
}
