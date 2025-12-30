package by.ruslan.radzevich.carsharingservice.service;

import by.ruslan.radzevich.carsharingservice.dto.request.CreateUserRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.response.CreateUserResponseDto;

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
}
