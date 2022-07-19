package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.dao.RoomRepository;
import com.codecool.hogwarts_potions.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public List<Room> getAllRooms() {

        return roomRepository.findAll();
    }

    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    public Room getRoomById(Long id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isEmpty()) {
            return null;
        }
        return roomOptional.get();
    }

    public void updateRoomById(Long id, Room updatedRoom) {
       if (roomRepository.existsById(id)) {
           updatedRoom.setId(id);
           roomRepository.save(updatedRoom);
       }

    }

    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }

    public List<Room> getRoomsForRatOwners() {
        return roomRepository.findRoomForRatOwner();
    }
}
