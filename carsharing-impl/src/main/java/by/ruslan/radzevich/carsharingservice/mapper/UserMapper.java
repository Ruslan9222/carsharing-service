package by.ruslan.radzevich.carsharingservice.mapper;


import by.ruslan.radzevich.carsharingservice.dto.request.CreateUserRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.response.CreateUserResponseDto;
import by.ruslan.radzevich.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Преобразует DTO-запрос {@link CreateUserRequestDto} в сущность {@link User}.
     *
     * @param createUserRequestDto входные данные для создания пользователя
     * @return сущность {@link User}, готовая для сохранения в базе
     */
    User mapToEntity(CreateUserRequestDto createUserRequestDto);

    /**
     * Преобразует сущность {@link User} в DTO-ответ {@link CreateUserResponseDto}.
     *
     * @param user сохранённая сущность пользователя
     * @return DTO-ответ с идентификатором созданного пользователя
     */
    CreateUserResponseDto mapToResponseDto(User user);
}
