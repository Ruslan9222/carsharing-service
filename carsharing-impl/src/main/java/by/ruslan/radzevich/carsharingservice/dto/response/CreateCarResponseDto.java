package by.ruslan.radzevich.carsharingservice.dto.response;

import lombok.Builder;

/**
 * DTO-ответ для операции создания автомобиля.
 *
 * @author Ruslan
 */
@Builder
public record CreateCarResponseDto(
    Long id
) {

}
