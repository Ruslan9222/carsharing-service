package by.ruslan.radzevich.carsharingservice.service.impl;

import by.ruslan.radzevich.carsharingservice.dto.request.CreateUserRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.response.CreateUserResponseDto;
import by.ruslan.radzevich.carsharingservice.exception.UserException;
import by.ruslan.radzevich.carsharingservice.mapper.UserMapper;
import by.ruslan.radzevich.carsharingservice.service.UserService;
import by.ruslan.radzevich.model.entity.User;
import by.ruslan.radzevich.model.enums.Role;
import by.ruslan.radzevich.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public CreateUserResponseDto create(CreateUserRequestDto createUser) {
        if(userRepository.existsByEmail(createUser.email())){
           throw new UserException("Пользователь с таким Email уже существует");
        }
        User user = userMapper.mapToEntity(createUser);
        user.setRoleList(Set.of(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saveUser = userRepository.save(user);
        return userMapper.mapToResponseDto(saveUser);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(UserException::new);

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
