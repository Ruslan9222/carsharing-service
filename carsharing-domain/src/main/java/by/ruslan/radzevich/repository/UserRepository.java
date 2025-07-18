//package by.ruslan.radzevich.repository;
//
//
//import by.ruslan.radzevich.view.UserView;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//
//public interface UserRepository extends JpaRepository<by.ruslan.radzevich.model.entity.User, Long> {
//    List<UserView> findAllBy();
//    @Modifying
//    @Query("update User u set u.email = :email where u.id = :id")
//    void updateEmailByID(@Param(value = "id") long id, @Param(value = "email") String email);
//
//    Optional<by.ruslan.radzevich.model.entity.User> findByUsername(String username);
//}
