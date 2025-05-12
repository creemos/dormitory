package my.kaytmb.dormitory.repository;


import my.kaytmb.dormitory.entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kay 26.03.2025
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("select s from Student s where s.room.id = :id")
    List<Student> findStudentsByRoom(Integer id);

    List<Student> findAll(Sort sort);

    @Query("select s from Student s where s.university.id = :id and s.isLiving is false")
    List<Student> findStudentsByUniversity(Integer id);
}
