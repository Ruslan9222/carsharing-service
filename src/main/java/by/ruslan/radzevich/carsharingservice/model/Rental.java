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
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long carId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double costPerMinute;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
