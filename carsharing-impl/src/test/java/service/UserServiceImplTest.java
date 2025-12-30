package service;

import by.ruslan.radzevich.carsharingservice.configuration.JWTTokenProvider;
import by.ruslan.radzevich.carsharingservice.dto.request.CreateUserRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.response.CreateUserResponseDto;
import by.ruslan.radzevich.carsharingservice.exception.UserException;
import by.ruslan.radzevich.carsharingservice.mapper.UserMapper;
import by.ruslan.radzevich.carsharingservice.service.impl.UserServiceImpl;
import by.ruslan.radzevich.model.entity.User;
import by.ruslan.radzevich.model.enums.Role;
import by.ruslan.radzevich.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private UserMapper userMapper;
    @Mock
    private JWTTokenProvider tokenProvider;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Создание пользователя: успешное сохранение при уникальном email")
    void create_ShouldSaveUser_WhenEmailNotExists() {
        CreateUserRequestDto dto = new CreateUserRequestDto("Ivan", "ivan", "pass",
                "pass", "ivan@mail.com");
        User userEntity = new User();
        userEntity.setUsername("ivan");
        userEntity.setPassword("pass");
        userEntity.setEmail("ivan@mail.com");
        CreateUserResponseDto responseDto = new CreateUserResponseDto(1L);
        when(userRepository.existsByEmail(dto.email())).thenReturn(false);
        when(userMapper.mapToEntity(dto)).thenReturn(userEntity);
        when(passwordEncoder.encode("pass")).thenReturn("encodedPass");
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.mapToResponseDto(userEntity)).thenReturn(responseDto);
        CreateUserResponseDto result = userService.create(dto);
        assertEquals(1L, result.id());
        verify(userRepository).save(userEntity);
    }

    @Test
    @DisplayName("Создание пользователя: выбрасывается исключение при существующем email")
    void create_ShouldThrowException_WhenEmailExists() {
        CreateUserRequestDto dto = new CreateUserRequestDto("Ivan", "ivan",
                "pass", "pass", "ivan@mail.com");
        when(userRepository.existsByEmail(dto.email())).thenReturn(true);
        assertThrows(UserException.class, () -> userService.create(dto));
    }

    @Test
    @DisplayName("Аутентификация: возвращается JWT токен при корректном пароле")
    void authenticate_ShouldReturnToken_WhenPasswordMatches() {
        User userEntity = new User();
        userEntity.setUsername("ivan");
        userEntity.setPassword("encodedPass");
        userEntity.setRoleList(Set.of(Role.USER));
        when(userRepository.findByUsername("ivan")).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches("rawPass", "encodedPass")).thenReturn(true);
        when(tokenProvider.generateToken(eq("ivan"), any())).thenReturn("jwtToken");
        String token = userService.authenticate("ivan", "rawPass");
        assertEquals("jwtToken", token);
    }

    @Test
    @DisplayName("Аутентификация: выбрасывается BadCredentialsException при неверном пароле")
    void authenticate_ShouldThrowException_WhenPasswordNotMatches() {
        User userEntity = new User();
        userEntity.setUsername("ivan");
        userEntity.setPassword("encodedPass");
        userEntity.setRoleList(Set.of(Role.USER));
        when(userRepository.findByUsername("ivan")).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches("wrongPass", "encodedPass")).thenReturn(false);
        assertThrows(BadCredentialsException.class, () -> userService.authenticate("ivan", "wrongPass"));
    }

    @Test
    @DisplayName("Загрузка пользователя: возвращается UserDetails при существующем пользователе")
    void loadUserByUsername_ShouldReturnUserDetails_WhenUserExists() {
        User userEntity = new User();
        userEntity.setUsername("ivan");
        userEntity.setPassword("encodedPass");
        userEntity.setRoleList(Set.of(Role.USER));
        when(userRepository.findByUsername("ivan")).thenReturn(Optional.of(userEntity));
        UserDetails userDetails = userService.loadUserByUsername("ivan");
        assertEquals("ivan", userDetails.getUsername());
        assertEquals("encodedPass", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("USER")));
    }

    @Test
    @DisplayName("Загрузка пользователя: выбрасывается UserException при отсутствии пользователя")
    void loadUserByUsername_ShouldThrowException_WhenUserNotFound() {
        when(userRepository.findByUsername("ivan")).thenReturn(Optional.empty());
        assertThrows(UserException.class, () -> userService.loadUserByUsername("ivan"));
    }
}
