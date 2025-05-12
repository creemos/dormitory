package my.kaytmb.dormitory.service;


import my.kaytmb.dormitory.dto.RoomRequest;
import my.kaytmb.dormitory.entity.Dormitory;
import my.kaytmb.dormitory.entity.Room;
import my.kaytmb.dormitory.entity.Student;
import my.kaytmb.dormitory.repository.DormitoryRepository;
import my.kaytmb.dormitory.repository.RoomRepository;
import my.kaytmb.dormitory.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author kay 25.03.2025
 */
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final DormitoryRepository dormitoryRepository;
    private final StudentRepository studentRepository;

    public RoomService(RoomRepository roomRepository, DormitoryRepository dormitoryRepository, StudentRepository studentRepository) {
        this.roomRepository = roomRepository;
        this.dormitoryRepository = dormitoryRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Integer createRoom(RoomRequest roomRequest) {
        Room room = new Room();
        room.setNumber(roomRequest.getNumber());
        room.setCapacity(roomRequest.getCapacity());
        room.setGender(roomRequest.getGender());
        room.setIsAvailable(roomRequest.getIsAvailable());
        room.setDormitory(dormitoryRepository.getReferenceById(roomRequest.getDormitoryId()));
        roomRepository.save(room);
        return room.getId();
    }

    @Transactional
    public void updateRoom(RoomRequest roomRequest) {
        Room room = roomRepository.getReferenceById(roomRequest.getId());
        room.setNumber(roomRequest.getNumber());
        room.setCapacity(roomRequest.getCapacity());
        room.setGender(roomRequest.getGender());
        room.setIsAvailable(roomRequest.getIsAvailable());
        room.setDormitory(dormitoryRepository.getReferenceById(roomRequest.getDormitoryId()));
        roomRepository.save(room);
    }

    @Transactional
    public Room getRoom(Integer id) {
        return roomRepository.getReferenceById(id);
    }

    public Dormitory getDormitory(Integer id) {
        return dormitoryRepository.getReferenceById(id);
    }

    @Transactional
    public Room findByNumberAndDormitoryId(Integer number, Integer dormitoryId) {
        Optional<Room> room = roomRepository.findByNumberAndDormitoryId(number, dormitoryId);
        if (room.isPresent()) {
            return room.get();
        } else {
            Room newRoom = new Room();
            newRoom.setNumber(number);
            return newRoom;
        }
    }

    @Transactional
    public void deleteRoom(Integer id) {
        roomRepository.deleteById(id);
    }

    @Transactional
    public List<Student> getStudentsByRoom(Integer id) {
        return studentRepository.findStudentsByRoom(id);
    }

    @Transactional
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

}
