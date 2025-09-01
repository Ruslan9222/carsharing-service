package by.ruslan.radzevich.carsharingservice.dto;

import by.ruslan.radzevich.carsharingservice.anotations.PasswordMatchers;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;


/**
 * DTO для создания нового пользователя.
 *
 * @author ruslan
 */
@Builder
@PasswordMatchers
public record CreateUserDto(

    @Schema(description = "Полное имя пользователя",
        example = "Иван")
    @NotBlank(message = "Please enter name")
    String name,

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
    String password,

    @Schema(description = "Подтверждение пароля. Должно совпадать с полем password",
        example = "StrongPass!2025")
    @NotBlank(message = "Please confirm password")
    String confirmPassword,

    @Schema(description = "Email пользователя. Должен быть уникальным и корректным",
        example = "ruslan@carsharing.by")
    @NotBlank(message = "User email is required")
    @Email(message = "It should have email format")
    String email

) {


}