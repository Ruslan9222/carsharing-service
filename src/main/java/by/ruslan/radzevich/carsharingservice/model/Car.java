package by.ruslan.radzevich.carsharingservice.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "db_car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car extends AbstractModelId {
    private String model;
    @Column(unique = true)
    private String winCode;
    private String classCar;
    private double latitude;
    private double longitude;
    private byte[] photo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(latitude, car.latitude) == 0 && Double.compare(longitude, car.longitude) == 0 && Objects.equals(model, car.model) && Objects.equals(winCode, car.winCode) && Objects.equals(classCar, car.classCar) && Arrays.equals(photo, car.photo);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(model, winCode, classCar, latitude, longitude);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
