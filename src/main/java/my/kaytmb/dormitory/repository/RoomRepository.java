package my.kaytmb.dormitory.repository;


import my.kaytmb.dormitory.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author kay 25.03.2025
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

}
