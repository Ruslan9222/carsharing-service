package by.ruslan.radzevich.carsharingservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
