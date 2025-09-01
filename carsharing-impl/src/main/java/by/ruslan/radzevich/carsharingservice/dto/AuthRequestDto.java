package by.ruslan.radzevich.carsharingservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;




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
