package by.ruslan.radzevich.carsharingservice.mapper;


import by.ruslan.radzevich.carsharingservice.dto.request.CreateUserRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.response.CreateUserResponseDto;
import by.ruslan.radzevich.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapToEntity(CreateUserRequestDto createUserRequestDto);

    CreateUserResponseDto mapToResponseDto (User user);
}
