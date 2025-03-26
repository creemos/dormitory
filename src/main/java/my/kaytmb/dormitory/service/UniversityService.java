package my.kaytmb.dormitory.service;


import my.kaytmb.dormitory.dto.UniversityRequest;
import my.kaytmb.dormitory.entity.University;
import my.kaytmb.dormitory.repository.UniversityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kay 26.03.2025
 */
@Service
public class UniversityService {

    UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Transactional
    public Integer createUniversity(UniversityRequest request) {
        University university = new University();
        university.setName(request.getName());
        university.setDuration(request.getDuration());
        universityRepository.save(university);
        return university.getId();
    }

    @Transactional
    public void updateUniversity(UniversityRequest request) {
        University university = universityRepository.getReferenceById(request.getId());
        university.setName(request.getName());
        university.setDuration(request.getDuration());
        universityRepository.save(university);
    }

    @Transactional
    public University getUniversity(Integer id) {
        return universityRepository.getReferenceById(id);
    }

    @Transactional
    public void deleteUniversity(Integer id) {
        universityRepository.deleteById(id);
    }

    @Transactional
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }



}
