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
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@ToString
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @Column(name = "login")
    String login;

    @Column(name = "password")
    String password;

    @Column(name = "role")
    String role;

    @Column(name = "surname")
    String surname;

    @Column(name = "firstname")
    String firstname;

    @Column(name = "patrname")
    String patrname;

}
