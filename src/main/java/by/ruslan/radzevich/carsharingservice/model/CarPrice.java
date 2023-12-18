package by.ruslan.radzevich.carsharingservice.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
    private Car car;

}
