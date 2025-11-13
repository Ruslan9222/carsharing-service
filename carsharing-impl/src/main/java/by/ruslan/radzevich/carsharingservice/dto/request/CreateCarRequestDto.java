package by.ruslan.radzevich.carsharingservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import lombok.Builder;

/**
 *
 * @author Ruslan
 * DTO-запрос для создания автомобиля.
 * <p>
 * Используется для передачи данных при создании нового объекта автомобиля
 * через REST API. Включает модель, VIN-код, класс автомобиля и цену.
 * </p>
 *
 * @param model    Модель автомобиля. Не может быть пустой.
 *                 <br>Пример: <code>BMW</code>
 *
 * @param winCode  VIN-код автомобиля (Vehicle Identification Number).
 *                 Должен состоять ровно из 17 символов: латинские буквы и цифры,
 *                 исключая буквы I, O, Q.
 *                 <br>Пример: <code>1HGCM82633A004352</code>
 *
 * @param classCar Класс автомобиля (тип кузова, модификация).
 *                 Не может быть пустым.
 *                 <br>Пример: <code>avant</code>
 *
 * @param price    Цена автомобиля. Не может быть пустой.
 *                 <br>Пример: <code>10000</code>
 */

@Builder
public record CreateCarRequestDto(

    @NotBlank(message = "Поле не может быть пустым")
    @Schema(defaultValue = "BMW", description = "Default model")
    String model,

    @Pattern(
        regexp = "^[A-HJ-NPR-Z0-9]{17}$",
        message = "VIN должен состоять из 17 символов (латинские буквы и цифры, без I, O, Q)"
    )
    @NotBlank(message = "Поле не может быть пустым")
    @Schema(defaultValue = "1HGCM82633A004352", description = "Default vin")
    String winCode,

    @NotBlank(message = "Поле не может быть пустым")
    @Schema(defaultValue = "avant", description = "Default classCar")
    String classCar,

    @NotNull(message = "Поле не может быть пустым")
    @Schema(defaultValue = "10000", description = "Default price")
    BigDecimal price
) {

}
