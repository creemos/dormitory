package my.kaytmb.dormitory.dto;


import lombok.Data;

import java.time.LocalDate;

/**
 * @author kay 26.03.2025
 */
@Data
public class StudentRequest {

    Integer id;

    String surname;

    String firstname;

    String patrname;

    Integer gender;

    Integer universityId;

    Integer dormitoryId;

    Integer roomId;

    LocalDate inDate;

    LocalDate outDate;

    Boolean isLiving;

}
