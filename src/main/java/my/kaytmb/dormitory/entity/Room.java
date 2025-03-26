package my.kaytmb.dormitory.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


/**
 * @author kay 25.03.2025
 */
@Entity
@Table(name = "room")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@ToString
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @Column(name = "number")
    Integer number;

    @ManyToOne
    @JoinColumn(name = "dormitory_id")
    Dormitory dormitory;

    @Column(name = "capacity")
    Integer capacity;

    @Column(name = "is_available")
    Boolean isAvailable;

    @Column(name = "gender_id")
    Integer gender;

}
