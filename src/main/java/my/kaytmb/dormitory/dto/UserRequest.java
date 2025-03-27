package my.kaytmb.dormitory.dto;


import lombok.Data;

/**
 * @author kay 26.03.2025
 */
@Data
public class UserRequest {

    Integer id;

    String login;

    String password;

    String role;

    String surname;

    String firstname;

    String patrname;

    Boolean isBlocked;

}
