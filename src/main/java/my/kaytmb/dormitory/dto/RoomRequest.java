package my.kaytmb.dormitory.dto;

import lombok.Data;

/**
 * @author kay 26.03.2025
 */
@Data
public class RoomRequest {

    Integer id;

    Integer number;

    Integer dormitoryId;

    Integer capacity;

    Boolean isAvailable;

    Integer gender;

}
