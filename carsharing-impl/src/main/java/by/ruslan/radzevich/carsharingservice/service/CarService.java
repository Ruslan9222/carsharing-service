package by.ruslan.radzevich.carsharingservice.service;

import by.ruslan.radzevich.carsharingservice.dto.request.CreateCarRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.response.CreateCarResponseDto;
import by.ruslan.radzevich.model.entity.Car;
import java.util.List;

/**
 * Сервисный интерфейс для работы с автомобилями в системе каршеринга.
 *
 * @author Ruslan
 */
public interface CarService {

    /**
     * Создаёт новый автомобиль на основе входных данных запроса.
     *
     * @param createCarRequestDto DTO-запрос с данными автомобиля
     * @return DTO-ответ с идентификатором созданного автомобиля
     */
    CreateCarResponseDto createCar(CreateCarRequestDto createCarRequestDto);

    List<Car> findCarsNearby(double latitude, double longitude, double radius);

}
