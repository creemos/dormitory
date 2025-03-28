package my.kaytmb.dormitory.rest;


import my.kaytmb.dormitory.dto.RoomRequest;
import my.kaytmb.dormitory.entity.Room;
import my.kaytmb.dormitory.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author kay 25.03.2025
 */
@Controller
@RequestMapping("/api/rooms/")
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createRoom(@RequestBody RoomRequest roomRequest) {
        Integer id = service.createRoom(roomRequest);
        return ResponseEntity.ok("New room created, ID: " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getRoomById(@PathVariable Integer id) {
        Room room = service.getRoom(id);
        return ResponseEntity.ok("Room with ID: " + id + ": " + room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRoom(@RequestBody RoomRequest roomRequest) {
        service.updateRoom(roomRequest);
        return ResponseEntity.ok("Room updated, ID: " + roomRequest.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Integer id) {
        service.deleteRoom(id);
        return ResponseEntity.ok("Room is deleted, ID: " + id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = service.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

}
