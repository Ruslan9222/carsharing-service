package by.ruslan.radzevich.carsharingservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

/**
 * DTO для авторизаций пользователя.
 *
 * @author ruslan
 */
@Builder
public record AuthRequestDto(

        @Schema(description = "Уникальное имя пользователя (логин)",
                example = "Иванов")
        @NotBlank(message = "Please enter username")
        String username,

        @Schema(description = "Пароль пользователя. Минимум 8 символов,"
                + " рекомендуется использовать спецсимволы",
                example = "StrongPass!2025")

        @NotBlank(message = "Please enter password")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
                message = "Password must be at least 8 characters long and contain letters and digits"
        )
        String password
) {


}
