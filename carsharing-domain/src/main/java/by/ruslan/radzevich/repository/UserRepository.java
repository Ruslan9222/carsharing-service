package by.ruslan.radzevich.repository;

import by.ruslan.radzevich.model.entity.User;
import by.ruslan.radzevich.view.UserView;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    List<UserView> findAllBy();

    @Modifying
    @Query("UPDATE User u SET u.email = :email WHERE u.id = :id")
    void updateEmailByID(@Param(value = "id") long id, @Param(value = "email") String email);

    Optional<User> findByUsername(String username);
}
