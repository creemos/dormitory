package my.kaytmb.dormitory.repository;


import my.kaytmb.dormitory.entity.Dormitory;
import my.kaytmb.dormitory.entity.Room;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kay 26.03.2025
 */
@Repository
public interface DormitoryRepository extends JpaRepository<Dormitory, Integer> {
    List<Dormitory> findAll(Sort sort);
    @Query("select r from Room r where r.dormitory.id = :id")
    List<Room> getRoomsByDormitory(Integer id, Sort sort);
}
