package by.ruslan.radzevich.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "db_card")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Card extends AbstractModelId {

    private Integer number;

    private String cv;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id")
    private User user;


}
