package by.ruslan.radzevich.carsharingservice.dto.request;

import by.ruslan.radzevich.carsharingservice.anotations.PasswordMatchers;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public record CreateUserRequestDto(

    @Schema(description = "Полное имя пользователя",
        example = "Иван")
    @NotBlank(message = "Please enter name")
    @JsonProperty("name") String name,

    @Schema(description = "Уникальное имя пользователя (логин)",
        example = "Иванов")
    @NotBlank(message = "Please enter username")
    @JsonProperty("username") String username,

    @Schema(description = "Пароль пользователя. Минимум 8 символов,"
                          + " рекомендуется использовать спецсимволы",
        example = "StrongPass!2025")
    @NotBlank(message = "Please enter password")
    @Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
        message = "Password must be at least 8 characters long and contain letters and digits"
    )
    @JsonProperty("password") String password,

    @Schema(description = "Подтверждение пароля. Должно совпадать с полем password",
        example = "StrongPass!2025")
    @NotBlank(message = "Please confirm password")
    @JsonProperty("confirmPassword") String confirmPassword,

    @Schema(description = "Email пользователя. Должен быть уникальным и корректным",
        example = "ruslan@carsharing.by")
    @NotBlank(message = "User email is required")
    @Email(message = "It should have email format")
    @JsonProperty("email") String email

) {


}