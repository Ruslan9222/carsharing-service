package by.ruslan.radzevich.carsharingservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class CreateUserDto {
    @NotBlank(message = "Please enter name")
    private String name;
    @NotBlank(message = "Please enter username")
    private String username;
    @NotBlank(message = "Please enter password")
    private String password;
    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    private String email;
}
