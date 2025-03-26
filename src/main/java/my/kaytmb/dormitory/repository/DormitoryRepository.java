package my.kaytmb.dormitory.repository;


import my.kaytmb.dormitory.entity.Dormitory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kay 26.03.2025
 */
@Repository
public interface DormitoryRepository extends JpaRepository<Dormitory, Integer> {
}
