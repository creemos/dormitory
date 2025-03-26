package my.kaytmb.dormitory.service;


import my.kaytmb.dormitory.dto.StudentRequest;
import my.kaytmb.dormitory.entity.Student;
import my.kaytmb.dormitory.repository.DormitoryRepository;
import my.kaytmb.dormitory.repository.RoomRepository;
import my.kaytmb.dormitory.repository.StudentRepository;
import my.kaytmb.dormitory.repository.UniversityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        student.setDormitory(dormitoryRepository.getReferenceById(request.getDormitoryId()));
        student.setRoom(roomRepository.getReferenceById(request.getRoomId()));
        student.setInDate(request.getInDate());
        student.setOutDate(request.getOutDate());
        student.setIsLiving(request.getIsLiving());
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
        student.setDormitory(dormitoryRepository.getReferenceById(request.getDormitoryId()));
        student.setRoom(roomRepository.getReferenceById(request.getRoomId()));
        student.setInDate(request.getInDate());
        student.setOutDate(request.getOutDate());
        student.setIsLiving(request.getIsLiving());
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
        return studentRepository.findAll();
    }

}
