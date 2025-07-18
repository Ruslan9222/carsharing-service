package by.ruslan.radzevich.model.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class AbstractModelId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
