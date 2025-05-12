package my.kaytmb.dormitory.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    Integer gender;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate inDate;

    @Column(name = "out_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate outDate;

    @Column(name = "is_living")
    Boolean isLiving;

    public Integer getUniversityId() {
        return university.getId();
    }

    public String getUniversityName() {
        return university.getName();
    }


}
