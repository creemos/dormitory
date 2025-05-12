package my.kaytmb.dormitory.service;


import my.kaytmb.dormitory.dto.StudentRequest;
import my.kaytmb.dormitory.entity.Student;
import my.kaytmb.dormitory.repository.DormitoryRepository;
import my.kaytmb.dormitory.repository.RoomRepository;
import my.kaytmb.dormitory.repository.StudentRepository;
import my.kaytmb.dormitory.repository.UniversityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author kay 26.03.2025
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UniversityRepository universityRepository;
    private final DormitoryRepository dormitoryRepository;
    private final RoomRepository roomRepository;

    public StudentService(StudentRepository studentRepository,
                          UniversityRepository universityRepository,
                          DormitoryRepository dormitoryRepository,
                          RoomRepository roomRepository) {
        this.studentRepository = studentRepository;
        this.universityRepository = universityRepository;
        this.dormitoryRepository = dormitoryRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public Integer createStudent(StudentRequest request) {
        Student student = new Student();
        student.setSurname(request.getSurname());
        student.setFirstname(request.getFirstname());
        student.setPatrname(request.getPatrname());
        student.setGender(request.getGender());
        student.setUniversity(universityRepository.getReferenceById(request.getUniversityId()));
        student.setDormitory(request.getDormitoryId() != null ? dormitoryRepository.getReferenceById(request.getDormitoryId()) : null);
        student.setRoom(request.getRoomId() != null ? roomRepository.getReferenceById(request.getRoomId()) : null);
        student.setInDate(request.getInDate());
        student.setOutDate(request.getOutDate());
        student.setIsLiving(false);
        studentRepository.save(student);
        return student.getId();
    }

    @Transactional
    public void updateStudent(StudentRequest request) {
        Student student = studentRepository.getReferenceById(request.getId());
        student.setSurname(request.getSurname());
        student.setFirstname(request.getFirstname());
        student.setPatrname(request.getPatrname());
        student.setGender(request.getGender());
        student.setUniversity(universityRepository.getReferenceById(request.getUniversityId()));
        student.setDormitory(request.getDormitoryId() != null ? dormitoryRepository.getReferenceById(request.getDormitoryId()) : null);
        student.setRoom(request.getRoomId() != null ? roomRepository.getReferenceById(request.getRoomId()) : null);
        student.setInDate(request.getInDate());
        student.setOutDate(request.getOutDate());
        studentRepository.save(student);
    }


    @Transactional
    public Student getStudent(Integer id) {
        return studentRepository.getReferenceById(id);
    }

    @Transactional
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public List<Student> getAllStudents() {
        Sort sort = Sort.by(Sort.Order.asc("id"));
        return studentRepository.findAll(sort);
    }

    @Transactional
    public Student excludePatient(Integer id) {
        Student student = studentRepository.getReferenceById(id);
        student.setOutDate(LocalDate.now());
        student.setIsLiving(false);
        return studentRepository.save(student);
    }

    @Transactional
    public Map<Integer, String> getAvailableStudents(Integer universityId) {
        List<Student> students = studentRepository.findStudentsByUniversity(universityId);
        return students.stream().collect(Collectors.toMap(Student::getId,
                student -> String.format("%s %s %s", student.getSurname(), student.getFirstname(), student.getPatrname())));
    }

}
