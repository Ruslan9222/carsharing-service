package by.ruslan.radzevich.carsharingservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "db_rental")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rental extends AbstractModelId{
    private Long carId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double costPerMinute;
    private byte[] photo;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
