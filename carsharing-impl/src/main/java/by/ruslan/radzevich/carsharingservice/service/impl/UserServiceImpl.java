package by.ruslan.radzevich.carsharingservice.service.impl;

import by.ruslan.radzevich.carsharingservice.configuration.JWTTokenProvider;
import by.ruslan.radzevich.carsharingservice.dto.request.CreateUserRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.response.CreateUserResponseDto;
import by.ruslan.radzevich.carsharingservice.exception.UserException;
import by.ruslan.radzevich.carsharingservice.mapper.UserMapper;
import by.ruslan.radzevich.carsharingservice.service.UserService;
import by.ruslan.radzevich.carsharingservice.utils.ConstrainExceptionUtils;
import by.ruslan.radzevich.model.entity.User;
import by.ruslan.radzevich.model.enums.Role;
import by.ruslan.radzevich.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private final JWTTokenProvider tokenProvider;

    @Override
    @Transactional
    public CreateUserResponseDto create(CreateUserRequestDto createUser) {
        if (userRepository.existsByEmail(createUser.email())) {
            throw new UserException(ConstrainExceptionUtils.EMAIL_ALREADY_EXISTS);
        }

        User user = userMapper.mapToEntity(createUser);
        user.setRoleList(Set.of(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        return userMapper.mapToResponseDto(savedUser);
    }

    @Transactional
    public String authenticate(String username, String rawPassword) {
        UserDetails userDetails = loadUserByUsername(username);

        if (!passwordEncoder.matches(rawPassword, userDetails.getPassword())) {
            throw new BadCredentialsException(ConstrainExceptionUtils.INVALID_USERNAME_OR_PASSWORD);
        }

        return tokenProvider.generateToken(userDetails.getUsername(), userDetails.getAuthorities());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException(ConstrainExceptionUtils.USER_NOT_FOUND + username));

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
