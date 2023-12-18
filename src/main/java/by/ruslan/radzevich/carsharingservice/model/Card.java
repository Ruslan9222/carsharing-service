package by.ruslan.radzevich.carsharingservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "db_card")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Card extends AbstractModelId {
    private Integer number;
    private String cv;
    private Integer name;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User ownerCard;



}
