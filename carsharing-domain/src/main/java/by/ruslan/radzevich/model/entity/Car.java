package by.ruslan.radzevich.model.entity;

import java.math.BigDecimal;
import lombok.*;

import javax.persistence.*;
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
    private BigDecimal price;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


}
