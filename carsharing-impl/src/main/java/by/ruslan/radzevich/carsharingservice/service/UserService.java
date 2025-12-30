package by.ruslan.radzevich.carsharingservice.service;

import by.ruslan.radzevich.carsharingservice.dto.request.CreateUserRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.response.CreateUserResponseDto;

public interface UserService {

    CreateUserResponseDto create(CreateUserRequestDto createUser);
}
