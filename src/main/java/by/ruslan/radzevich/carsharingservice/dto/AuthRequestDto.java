package by.ruslan.radzevich.carsharingservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@ToString
public class AuthRequestDto {
    @NotEmpty
    @NotBlank(message = "Please enter username")
    private String username;
    @NotEmpty
    @NotBlank(message = "Please enter password")
    private String password;
}
