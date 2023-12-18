package by.ruslan.radzevich.carsharingservice.service;


import by.ruslan.radzevich.carsharingservice.exception.UserNotFoundException;
import by.ruslan.radzevich.carsharingservice.model.Role;
import by.ruslan.radzevich.carsharingservice.model.User;
import by.ruslan.radzevich.carsharingservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;

    public User create(User user) {
        user.setRoleList(Set.of(Role.USER));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername.orElseThrow(UserNotFoundException::new);
    }

    public void updateEmailWithCustomQuery(Long id, String email) {
        userRepository.updateEmailByID(id, email);
    }
}
