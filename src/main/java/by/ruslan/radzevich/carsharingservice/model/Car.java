package by.ruslan.radzevich.carsharingservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "db_car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car extends AbstractModelId {
    private String model;
    private String winCode;
    private double latitude;
    private double longitude;
    private byte[] photo;


}
