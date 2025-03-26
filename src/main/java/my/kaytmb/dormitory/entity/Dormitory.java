package my.kaytmb.dormitory.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;

/**
 * @author kay 25.03.2025
 */
@Entity
@Table(name = "dormitory")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@ToString
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Dormitory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @Column(name = "number")
    Integer number;

    @Column(name = "room_count")
    Integer roomCount;

    @ManyToOne
    @JoinColumn(name = "university_id")
    University university;

    @Column(name = "is_available")
    Boolean isAvailable;

}
