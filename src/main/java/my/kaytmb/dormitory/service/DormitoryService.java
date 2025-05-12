package my.kaytmb.dormitory.service;


import my.kaytmb.dormitory.dto.DormitoryRequest;
import my.kaytmb.dormitory.entity.Dormitory;
import my.kaytmb.dormitory.entity.Room;
import my.kaytmb.dormitory.repository.DormitoryRepository;
import my.kaytmb.dormitory.repository.UniversityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kay 26.03.2025
 */
@Service
public class DormitoryService {

    DormitoryRepository dormitoryRepository;
    UniversityRepository universityRepository;

    public DormitoryService(DormitoryRepository dormitoryRepository, UniversityRepository universityRepository) {
        this.dormitoryRepository = dormitoryRepository;
        this.universityRepository = universityRepository;
    }

    @Transactional
    public Integer createDormitory(DormitoryRequest request) {
        Dormitory dormitory = new Dormitory();
        dormitory.setIsAvailable(request.getIsAvailable());
        dormitory.setNumber(request.getNumber());
        dormitory.setRoomCount(request.getRoomCount());
        dormitory.setUniversity(universityRepository.getReferenceById(request.getUniversityId()));
        dormitoryRepository.save(dormitory);
        return dormitory.getId();
    }

    @Transactional
    public void updateDormitory(DormitoryRequest request) {
        Dormitory dormitory = dormitoryRepository.getReferenceById(request.getId());
        dormitory.setIsAvailable(request.getIsAvailable());
        dormitory.setNumber(request.getNumber());
        dormitory.setRoomCount(request.getRoomCount());
        dormitory.setUniversity(universityRepository.getReferenceById(request.getUniversityId()));
        dormitoryRepository.save(dormitory);
    }

    @Transactional
    public Dormitory getDormitory(Integer id) {
        return dormitoryRepository.getReferenceById(id);
    }

    @Transactional
    public void deleteDormitory(Integer id) {
        dormitoryRepository.deleteById(id);
    }

    @Transactional
    public List<Dormitory> getAllDormitories() {
        Sort sort = Sort.by(Sort.Order.asc("id"));
        return dormitoryRepository.findAll(sort);
    }

    @Transactional
    public List<Room> getRooms(Integer id) {
        Sort sort = Sort.by(Sort.Order.asc("number"));
        return dormitoryRepository.getRoomsByDormitory(id, sort);
    }
}
