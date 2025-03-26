package my.kaytmb.dormitory.dto;


import lombok.Data;

import java.util.Date;

/**
 * @author kay 26.03.2025
 */
@Data
public class StudentRequest {

    Integer id;

    String surname;

    String firstname;

    String patrname;

    String gender;

    Integer universityId;

    Integer dormitoryId;

    Integer roomId;

    Date inDate;

    Date outDate;

    Boolean isLiving;

}
