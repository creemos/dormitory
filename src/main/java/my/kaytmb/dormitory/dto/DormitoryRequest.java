package my.kaytmb.dormitory.dto;


import lombok.Data;

/**
 * @author kay 26.03.2025
 */
@Data
public class DormitoryRequest {

    Integer id;

    Integer number;

    Integer roomCount;

    Integer universityId;

    Boolean isAvailable;

}
