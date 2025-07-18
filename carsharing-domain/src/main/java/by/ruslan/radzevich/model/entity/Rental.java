//package by.ruslan.radzevich.model.entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "db_rental")
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//public class Rental extends AbstractModelId{
//    private Long carId;
//    private LocalDateTime startTime;
//    private LocalDateTime endTime;
//    private double costPerMinute;
//    private byte[] photo;
//    private String comment;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private User user;
//}
