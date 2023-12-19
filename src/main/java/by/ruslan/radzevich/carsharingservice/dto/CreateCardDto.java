package by.ruslan.radzevich.carsharingservice.dto;

import lombok.*;

@Setter
@Getter
@ToString
public class CreateCardDto {
    private Integer number;
    private String cv;
    private String name;
}
