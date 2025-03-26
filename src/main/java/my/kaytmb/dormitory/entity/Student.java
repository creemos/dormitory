package my.kaytmb.dormitory.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import java.util.Date;

/**
 * @author kay 25.03.2025
 */
@Entity
@Table(name = "student")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@ToString
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @Column(name = "surname")
    String surname;

    @Column(name = "firstname")
    String firstname;

    @Column(name = "patrname")
    String patrname;

    @Column(name = "gender_id")
    String gender;

    @ManyToOne
    @JoinColumn(name = "university_id")
    University university;

    @ManyToOne
    @JoinColumn(name = "dormitory_id")
    Dormitory dormitory;

    @ManyToOne
    @JoinColumn(name = "room_id")
    Room room;

    @Column(name = "in_date")
    Date inDate;

    @Column(name = "out_date")
    Date outDate;

    @Column(name = "is_living")
    Boolean isLiving;

}
