package my.kaytmb.dormitory.repository;


import my.kaytmb.dormitory.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kay 26.03.2025
 */
@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}
