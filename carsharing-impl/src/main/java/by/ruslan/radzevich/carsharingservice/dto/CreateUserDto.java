package by.ruslan.radzevich.carsharingservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
public class CreateUserDto {
    @NotEmpty
    @NotBlank(message = "Please enter name")
    private String name;
    @NotEmpty
    @NotBlank(message = "Please enter username")
    private String username;
    @NotEmpty
    @NotBlank(message = "Please enter password")
    private String password;
    @NotEmpty
    @NotBlank(message = "Please confirm password")
    private String confirmPassword;
    @NotEmpty
    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    private String email;
}
