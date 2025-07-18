package by.ruslan.radzevich.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "db_carPrice")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarPrice extends AbstractModelId{
    private BigDecimal price;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

}
