package by.ruslan.radzevich.carsharingservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class PhotoCarDto {
    private MultipartFile photo;
}
