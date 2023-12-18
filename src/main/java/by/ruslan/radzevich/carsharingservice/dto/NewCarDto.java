package by.ruslan.radzevich.carsharingservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class NewCarDto {
    private String model;
    private String winCode;
    private String classCar;
    private BigDecimal price;
}
