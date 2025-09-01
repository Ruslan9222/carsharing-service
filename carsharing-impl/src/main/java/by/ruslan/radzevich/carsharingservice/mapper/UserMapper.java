package by.ruslan.radzevich.carsharingservice.mapper;

import by.ruslan.radzevich.carsharingservice.dto.request.CreateUserRequestDto;

import by.ruslan.radzevich.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User createUserDtoToUser(CreateUserRequestDto dto) {
        User user = new User();
        user.setName(dto.name());
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setPassword(dto.confirmPassword());
        user.setEmail(dto.email());
        return user;
    }
}
