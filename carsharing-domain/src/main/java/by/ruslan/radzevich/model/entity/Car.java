package by.ruslan.radzevich.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Класс, представляющий автомобиль в системе каршеринга.
 * <p>
 * Сущность хранит основную информацию об автомобиле, включая модель, VIN-код, класс кузова,
 * геопозицию, фото и цену. Используется для работы с таблицей {@code db_car} в базе данных.
 * </p>
 *
 * @author Ruslan
 */
@Entity
@Table(name = "db_car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car extends AbstractModelId {

    /**
     * Модель автомобиля.
     */
    private String model;

    /**
     * Уникальный VIN-код автомобиля (Vehicle Identification Number).
     * <p>
     */
    @Column(unique = true)
    private String winCode;

    /**
     * Класс автомобиля (тип кузова или модификация).
     */
    private String classCar;

    /**
     * Географическая широта местоположения автомобиля.
     */
    private double latitude;

    /**
     * Географическая долгота местоположения автомобиля.
     */
    private double longitude;

    /**
     * Фото автомобиля в виде массива байтов.
     */
    private byte[] photo;

    /**
     * Цена автомобиля.
     */
    private BigDecimal price;
}
