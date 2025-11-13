package by.ruslan.radzevich.carsharingservice.mapper;


import by.ruslan.radzevich.carsharingservice.dto.request.CreateCarRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.response.CreateCarResponseDto;
import by.ruslan.radzevich.model.entity.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    /**
     * Преобразует DTO-запрос {@link CreateCarRequestDto} в сущность {@link Car}.
     *
     * @param createCarRequestDto входные данные для создания автомобиля
     * @return сущность {@link Car}, готовая для сохранения в базе
     */
    Car mapToEntity(CreateCarRequestDto createCarRequestDto);

    /**
     * Преобразует сущность {@link Car} в DTO-ответ {@link CreateCarResponseDto}.
     *
     * @param car сохранённая сущность автомобиля
     * @return DTO-ответ с идентификатором созданного автомобиля
     */
    CreateCarResponseDto mapToResponseDto(Car car);

}
