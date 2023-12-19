package by.ruslan.radzevich.carsharingservice.mapper;

import by.ruslan.radzevich.carsharingservice.dto.CreateUserDto;
import by.ruslan.radzevich.carsharingservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User createUserDtoToUser(CreateUserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return user;
    }
}
