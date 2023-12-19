package by.ruslan.radzevich.carsharingservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class CreateUserDto {
    private String name;
    private String username;
    private String password;
    private String email;
}
