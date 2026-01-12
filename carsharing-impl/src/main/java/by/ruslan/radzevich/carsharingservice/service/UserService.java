package by.ruslan.radzevich.carsharingservice.service;

import by.ruslan.radzevich.carsharingservice.dto.request.CreateUserRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.response.CreateUserResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Сервисный интерфейс для работы с пользователями.
 *
 * @author Ruslan
 */
public interface UserService {

    /**
     * Создаёт новыого пользователя основе входных данных запроса.
     *
     * @param createUser DTO-запрос с данными пользователя
     * @return DTO-ответ с идентификатором созданного пользователя
     */

    CreateUserResponseDto create(CreateUserRequestDto createUser);


    /**
     * Авторизовывает пользователя основе входных данных запроса.
     *
     * @param username имя пользователя
     * @param rawPassword пароль пользователя
     * @return Jwt token для выдачи доступов пользователю согласно роли
     */

    String authenticate(String username, String rawPassword);

    UserDetails loadUserByUsername(String username);
}
