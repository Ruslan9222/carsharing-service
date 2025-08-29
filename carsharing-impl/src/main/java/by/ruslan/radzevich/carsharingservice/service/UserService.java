package by.ruslan.radzevich.carsharingservice.service;

import by.ruslan.radzevich.carsharingservice.exception.UserNotFoundException;
import by.ruslan.radzevich.model.entity.User;
import by.ruslan.radzevich.model.enums.Role;
import by.ruslan.radzevich.repository.UserRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User create(User user) {
        user.setRoleList(Set.of(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(UserNotFoundException::new);

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .authorities(user.getRoleList())
            .build();
    }

    public void updateEmailWithCustomQuery(Long id, String email) {
        userRepository.updateEmailByID(id, email);
    }


}
