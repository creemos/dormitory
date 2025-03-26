package my.kaytmb.dormitory.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;

/**
 * @author kay 25.03.2025
 */
@Entity
@Table(name = "university")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@ToString
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "duration")
    Integer duration;

}
